package com.dwirandyh.cermatiproject.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.data.repository.UserRepository
import com.dwirandyh.cermatiproject.model.NetworkStatus
import com.dwirandyh.cermatiproject.utils.paging.PagingDataSourceFactory
import com.dwirandyh.cermatiproject.utils.paging.PagingListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(),
    PagingListener<GithubUser> {

    companion object {
        val TAG = MainViewModel::class.java.simpleName

        const val PAGE_SIZE = 30
    }


    val query: MutableLiveData<String> = MutableLiveData()
    val userPagedList: LiveData<PagedList<GithubUser>> =
        Transformations.switchMap(Transformations.distinctUntilChanged(query)) {
            // distincUntilChanged -> mengindari memanggil ulang ketika configuration changes ex: Rotasi
            search()
        }

    private var mNetworkStatus: MutableLiveData<NetworkStatus> = MutableLiveData()
    val networkStatus: LiveData<NetworkStatus>
        get() = mNetworkStatus

    private val mDisposable = CompositeDisposable()
    private val mPagingDataSourceFactory: PagingDataSourceFactory<GithubUser> =
        PagingDataSourceFactory(this)


    fun setQuery(value: String) {
        query.postValue(value)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    private fun search(): LiveData<PagedList<GithubUser>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .build()

        return LivePagedListBuilder<Int, GithubUser>(mPagingDataSourceFactory, config).build()
    }

    override fun loadInitial(loadInitialCallback: PageKeyedDataSource.LoadInitialCallback<Int, GithubUser>) {
        if (query.value.isNullOrEmpty()){
            mNetworkStatus.postValue(NetworkStatus(NetworkStatus.ERROR, true))
            return
        }

        mNetworkStatus.postValue(NetworkStatus(NetworkStatus.LOADING))
        val queryParam = query.value ?: ""
        mDisposable.add(
            userRepository.search(queryParam, 1)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            mNetworkStatus.postValue(NetworkStatus(NetworkStatus.NOT_FOUND, true))
                        } else {
                            mNetworkStatus.postValue(NetworkStatus(NetworkStatus.SUCCESS))
                            loadInitialCallback.onResult(it, null, 2)
                        }

                    },
                    {
                        mNetworkStatus.postValue(NetworkStatus(NetworkStatus.ERROR, true))
                        Log.e(TAG, it.message ?: "")
                    }
                )
        )
    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, GithubUser>
    ) {
        val queryParam = query.value ?: ""
        mDisposable.add(
            userRepository.search(queryParam, params.key)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it, params.key + 1)
                    },
                    {
                        Log.e(TAG, it.message ?: "")
                    }
                )
        )
    }
}