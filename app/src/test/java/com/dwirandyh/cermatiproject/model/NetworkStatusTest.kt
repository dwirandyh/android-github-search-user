package com.dwirandyh.cermatiproject.model

import android.net.Network
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class NetworkStatusTest {

    companion object {
        const val SUCCESS = 2
        const val NOT_FOUND = 3
        const val ERROR = -1
        const val LOADING = 1
    }


    @Test
    fun testNetworkStateSuccess() {
        val networkStatus = NetworkStatus(NetworkStatus.SUCCESS)
        assertEquals(SUCCESS, networkStatus.status)
    }

    @Test
    fun testNetworkStateNotFound() {
        val networkStatus = NetworkStatus(NetworkStatus.NOT_FOUND)
        assertEquals(NOT_FOUND, networkStatus.status)
    }

    @Test
    fun testNetworkStateError() {
        val networkStatus = NetworkStatus(NetworkStatus.ERROR)
        assertEquals(ERROR, networkStatus.status)
    }

    @Test
    fun testNetworkStateLoading() {
        val networkStatus = NetworkStatus(NetworkStatus.LOADING)
        assertEquals(LOADING, networkStatus.status)
    }
}