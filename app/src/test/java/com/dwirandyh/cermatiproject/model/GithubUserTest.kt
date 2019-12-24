package com.dwirandyh.cermatiproject.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class GithubUserTest {

    val oldGithubUser = GithubUser(
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

    @Test
    fun testAreItemsTheSame() {
        val newGithubUser = GithubUser(
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

        val isSame = GithubUser.DIFF_UTIL.areItemsTheSame(oldGithubUser, newGithubUser)
        assertEquals(isSame, true)
    }

    @Test
    fun testAreItemsDifferent() {
        val differentUser = GithubUser(
            12345,
            "https://avatars0.githubusercontent.com/u/1337260?v=4",
            "https://api.github.com/users/dwi/events{/privacy}",
            "https://api.github.com/users/dwi/followers",
            "https://api.github.com/users/dwi/following{/other_user}",
            "https://api.github.com/users/dwi/gists{/gist_id}",
            "",
            "https://github.com/dwi",
            "dwirandyh",
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
        val isSame = GithubUser.DIFF_UTIL.areItemsTheSame(oldGithubUser, differentUser)
        assertEquals(isSame, false)
    }

    @Test
    fun testAreContentsTheSame() {
        val newGithubUser = GithubUser(
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

        val isSame = GithubUser.DIFF_UTIL.areContentsTheSame(oldGithubUser, newGithubUser)
        assertEquals(isSame, true)
    }

    @Test
    fun testAreContentsDifferent() {
        val newGithubUser = GithubUser(
            1337260,
            "https://avatars0.githubusercontent.com/u/1337261?v=4",
            "https://api.github.com/users/dwi/events{/privacy}",
            "https://api.github.com/users/dwi/followers",
            "https://api.github.com/users/dwi/following{/other_user}",
            "https://api.github.com/users/dwi/gists{/gist_id}",
            "",
            "https://github.com/dwi",
            "dwirandy",
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

        val isSame = GithubUser.DIFF_UTIL.areContentsTheSame(oldGithubUser, newGithubUser)
        assertEquals(isSame, false)
    }
}