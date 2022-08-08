package com.example.closedpullrequest.presentation.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.closedpullrequest.api.models.PullRequestUser
import com.example.closedpullrequest.databinding.SingleItemPullRequestBinding
import com.example.closedpullrequest.util.getProgressDrawable
import com.example.closedpullrequest.util.loadImage

class PullRequestViewHolder(
    private val binding: SingleItemPullRequestBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PullRequestUser) {
        binding.title.text = data.title
        binding.createdDate.text = data.created_date
        binding.closedDate.text = data.closed_date
        binding.userName.text = data.user.name
        binding.imageView.loadImage(data.user.avatar_url, getProgressDrawable(context))
    }
}