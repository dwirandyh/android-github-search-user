package com.dwirandyh.cermatiproject.data.repository

import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.data.remote.UserEndpoint
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userEndpoint: UserEndpoint) :
    UserRepository {

    override fun search(query: String, page: Int): Observable<List<GithubUser>> {
        return userEndpoint.search(query, page)
            .flatMap {
                Observable.just(it.items)
            }
    }
}