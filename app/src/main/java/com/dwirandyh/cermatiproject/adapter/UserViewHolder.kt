package com.dwirandyh.cermatiproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dwirandyh.cermatiproject.model.GithubUser
import com.dwirandyh.cermatiproject.databinding.ItemUserBinding

class UserViewHolder(
    private val binding: ItemUserBinding,
    private val onClickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: GithubUser) {
        binding.user = user

        binding.root.setOnClickListener {
            onClickListener.onUserClick(user)
        }
    }

    interface OnClickListener {
        fun onUserClick(user: GithubUser)
    }
}