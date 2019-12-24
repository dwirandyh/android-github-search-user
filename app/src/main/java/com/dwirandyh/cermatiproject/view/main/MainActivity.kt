package com.dwirandyh.cermatiproject.view.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwirandyh.cermatiproject.R
import com.dwirandyh.cermatiproject.adapter.UserAdapter
import com.dwirandyh.cermatiproject.adapter.UserViewHolder
import com.dwirandyh.cermatiproject.databinding.ActivityMainBinding
import com.dwirandyh.cermatiproject.di.DaggerAppComponent
import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.model.NetworkStatus
import com.dwirandyh.cermatiproject.utils.RxViewObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), UserViewHolder.OnClickListener {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private val mUserAdapter = UserAdapter(this)

    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder().withContext(applicationContext).build().inject(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        initUserRecyclerView()
        subscribeUI()
    }

    private fun subscribeUI() {
        mViewModel.userPagedList.observe(this, Observer {
            mUserAdapter.submitList(it)
        })

        mViewModel.networkStatus.observe(this, Observer {
            when (it.status) {
                NetworkStatus.NOT_FOUND -> {
                    it.message = getString(R.string.error_not_found, mViewModel.query.value)
                }
                NetworkStatus.ERROR -> {
                    if (mViewModel.query.value.isNullOrEmpty()) {
                        it.message = getString(R.string.error_no_query)
                    } else {
                        it.message = getString(R.string.error_connection)
                    }
                }
            }
            mBinding.networkStatus = it
        })

        mDisposable.add(
            RxViewObservable.fromTextView(mBinding.editQuery)
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged() // prevent duplicate call with same query
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mViewModel.setQuery(it)
                    },
                    {
                        Log.e(TAG, it.message ?: "")
                    }
                )
        )
    }

    private fun initUserRecyclerView() {
        mBinding.recyclerUser.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerUser.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBinding.recyclerUser.adapter = mUserAdapter
    }

    override fun onUserClick(user: GithubUser) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }


}
