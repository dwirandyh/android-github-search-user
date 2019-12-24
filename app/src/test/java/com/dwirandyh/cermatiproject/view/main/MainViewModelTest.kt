package com.dwirandyh.cermatiproject.view.main

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dwirandyh.cermatiproject.data.repository.UserRepository
import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.model.NetworkStatus
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var networkStatusObserver: Observer<NetworkStatus>

    @Mock
    lateinit var userPagedList: Observer<PagedList<GithubUser>>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        // agar schedulers io berubah menjadi schedulers trampoline agar berurutan untuk testing
        RxJavaPlugins.setIoSchedulerHandler { scheduler -> Schedulers.trampoline() }

        viewModel = MainViewModel(userRepository)
        viewModel.networkStatus.observeForever(networkStatusObserver)
        viewModel.userPagedList.observeForever(userPagedList)
    }


    @Test
    fun testSearchSuccess() {
        val githuUserList = arrayListOf<GithubUser>(
            GithubUser(
                1337260,
                "https://avatars0.githubusercontent.com/u/1337260?v=4",
                "https://api.github.com/users/dwi/events{/privacy}",
                "https://api.github.com/users/dwi/followers",
                "https://api.github.com/users/dwi/following{/other_user}",
                "https://api.github.com/users/dwi/gists{/gist_id}",
                "",
                "https://github.com/dwi",
                "dwi",
                "MDQ6VXNlcjEzMzcyNjA=",
                "https://api.github.com/users/dwi/orgs",
                "https://api.github.com/users/dwi/received_events",
                "https://api.github.com/users/dwi/repos",
                517.468,
                false,
                "https://api.github.com/users/dwi/starred{/owner}{/repo}",
                "https://api.github.com/users/dwi/subscriptions",
                "User",
                "https://api.github.com/users/dwi"
            )
        )

        `when`(
            userRepository.search(
                anyString(),
                anyInt()
            )
        ).thenReturn(Observable.just(githuUserList))

        viewModel.setQuery("abc")
        verify(networkStatusObserver).onChanged(NetworkStatus(NetworkStatus.SUCCESS))
    }

    @Test
    fun testSearchEmptyQuery() {
        `when`(
            userRepository.search(
                anyString(),
                anyInt()
            )
        ).thenReturn(Observable.just(ArrayList()))

        viewModel.setQuery("")
        verify(networkStatusObserver).onChanged(NetworkStatus(NetworkStatus.ERROR, true))
    }

    @Test
    fun testSearchNotFound() {
        `when`(
            userRepository.search(
                anyString(),
                anyInt()
            )
        ).thenReturn(Observable.just(ArrayList()))

        viewModel.setQuery("abc")
        verify(networkStatusObserver).onChanged(NetworkStatus(NetworkStatus.NOT_FOUND, true))
    }

    @Test
    fun testSearchError() {
        `when`(
            userRepository.search(
                "abc",
                1
            )
        ).thenReturn(Observable.error(Throwable("API Error")))

        viewModel.setQuery("abc")
        verify(networkStatusObserver).onChanged(NetworkStatus(NetworkStatus.ERROR, true))
    }


}