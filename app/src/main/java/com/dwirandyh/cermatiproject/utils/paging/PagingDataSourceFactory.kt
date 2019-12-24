package com.dwirandyh.cermatiproject.utils.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class PagingDataSourceFactory<Model>(
    private val pagingListener: PagingListener<Model>
) : DataSource.Factory<Int, Model>() {

    val pagingDataSource = MutableLiveData<PagingDataSource<Model>>()

    override fun create(): DataSource<Int, Model> {
        val dataSource = PagingDataSource(pagingListener)
        pagingDataSource.postValue(dataSource)

        return dataSource
    }
}