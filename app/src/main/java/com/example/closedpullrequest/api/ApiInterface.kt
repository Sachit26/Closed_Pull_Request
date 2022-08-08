package com.example.closedpullrequest.api

import com.example.closedpullrequest.api.models.PullRequestUser
import retrofit2.http.GET

interface ApiInterface {

    @GET(ApiConstants.ALL_CLOSED_PULL_REQUEST_API)
    suspend fun getClosedPullRequest(): List<PullRequestUser>
}