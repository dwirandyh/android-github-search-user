package com.dwirandyh.cermatiproject.model


data class NetworkStatus(
    var status: Int,
    var showMessage: Boolean = false,
    var message: String = ""
) {
    companion object {
        const val SUCCESS = 2
        const val NOT_FOUND = 3
        const val ERROR = -1
        const val LOADING = 1
    }
}