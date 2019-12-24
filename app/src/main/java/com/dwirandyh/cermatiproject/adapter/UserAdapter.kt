package com.dwirandyh.cermatiproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.databinding.ItemUserBinding

class UserAdapter : PagedListAdapter<GithubUser, UserViewHolder>(GithubUser.DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}