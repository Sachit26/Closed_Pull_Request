package com.example.closedpullrequest.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedpullrequest.api.models.PullRequestUser
import com.example.closedpullrequest.repository.ApiRepository
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    var mUserList = MutableLiveData<List<PullRequestUser>>()
    val userList = mUserList as LiveData<List<PullRequestUser>>

    var mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage as LiveData<String>

    var mShowLoader = MutableLiveData<Boolean>()
    val showLoader = mShowLoader as LiveData<Boolean>

    fun refresh() {
        getUserList()
    }

    private fun getUserList() {
        mShowLoader.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = apiRepository.getPullRequest()
                    mShowLoader.postValue(false)
                    mUserList.postValue(result)
                } catch (throwable: Throwable) {
                    mShowLoader.postValue(false)
                    when (throwable) {
                        is IOException -> {
                            mErrorMessage.postValue("Network Error")
                        }
                        is HttpException -> {
                            val codeError = throwable.code()
                            val errorMessageResponse = throwable.message()
                            mErrorMessage.postValue("Error $errorMessageResponse : $codeError")
                        }
                        else -> {
                            mErrorMessage.postValue("Unknown error")
                        }
                    }
                }
            }
        }
    }

}