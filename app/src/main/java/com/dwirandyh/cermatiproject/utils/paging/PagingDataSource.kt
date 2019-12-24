package com.dwirandyh.cermatiproject.utils.paging

import androidx.paging.PageKeyedDataSource

class PagingDataSource<Model>(private val pagingListener: PagingListener<Model>) :
    PageKeyedDataSource<Int, Model>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Model>
    ) {
        pagingListener.loadInitial(callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Model>) {
        pagingListener.loadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Model>) {

    }
}