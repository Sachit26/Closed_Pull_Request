package com.example.closedpullrequest.repository

import com.example.closedpullrequest.api.ApiInterface

class ApiRepository(private val apiInterface: ApiInterface) {
    suspend fun getPullRequest() = apiInterface.getClosedPullRequest()
}