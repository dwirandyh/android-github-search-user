package com.dwirandyh.cermatiproject.data.repository

import com.dwirandyh.cermatiproject.model.GithubUser
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {
    fun search(query: String, page: Int): Observable<List<GithubUser>>
}