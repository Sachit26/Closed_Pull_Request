package com.example.closedpullrequest.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.closedpullrequest.R
import com.example.closedpullrequest.api.models.PullRequestUser

class PullRequestAdapter(
    private val pullRequest: ArrayList<PullRequestUser>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updatePullRequestList(newPullRequest: ArrayList<PullRequestUser>) {
        pullRequest.clear()
        pullRequest.addAll(newPullRequest)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.single_item_pull_request,
                parent, false
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return pullRequest.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PullRequestViewHolder).bind(
            pullRequest[position]
        )
    }
}
