package com.dwirandyh.cermatiproject.data.remote

import com.dwirandyh.cermatiproject.data.remote.response.GithubUserListResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserEndpoint {

    @GET("search/users")
    fun search(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Observable<GithubUserListResponse>
}