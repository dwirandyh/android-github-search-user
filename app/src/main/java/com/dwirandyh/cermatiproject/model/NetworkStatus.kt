package com.dwirandyh.cermatiproject.model


data class NetworkStatus(
    var status: Int,
    var showMessage: Boolean = false,
    var message: String = ""
) {
    companion object {
        const val SUCCESS = 200
        const val NOT_FOUND = 404
        const val ERROR = 500
        const val LOADING = 1
    }
}