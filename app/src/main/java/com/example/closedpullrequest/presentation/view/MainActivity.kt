package com.example.closedpullrequest.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedpullrequest.R
import com.example.closedpullrequest.api.models.PullRequestUser
import com.example.closedpullrequest.databinding.ActivityMainBinding
import com.example.closedpullrequest.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private lateinit var pullRequestAdapter: PullRequestAdapter
    var pullRequest: ArrayList<PullRequestUser> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initListener()
        initAdapter()
        initObserver()
        init()
    }

    private fun init() {
        mainViewModel.refresh()
    }

    private fun initListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mainViewModel.refresh()
        }
    }

    private fun initAdapter() {
        pullRequestAdapter = PullRequestAdapter(pullRequest)
        binding.rvPullRequestList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pullRequestAdapter
        }
    }

    private fun initObserver() {
        mainViewModel.showLoader.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    binding.rvPullRequestList.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.pbLoader.visibility = View.VISIBLE
                } else {
                    binding.pbLoader.visibility = View.GONE
                }
            }
        })
        mainViewModel.userList.observe(this, Observer { list ->
            pullRequestAdapter.updatePullRequestList(list as ArrayList<PullRequestUser>)
            binding.rvPullRequestList.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
        })
        mainViewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            binding.rvPullRequestList.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
        })
    }
}