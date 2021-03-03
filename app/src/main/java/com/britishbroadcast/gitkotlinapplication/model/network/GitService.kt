package com.britishbroadcast.gitkotlinapplication.model.network

import com.britishbroadcast.gitkotlinapplication.model.data.GitResponse
import com.britishbroadcast.gitkotlinapplication.util.Constants.Companion.API_PATH
import com.britishbroadcast.gitkotlinapplication.util.Constants.Companion.USER_NAME
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET(API_PATH)
    fun getRepositories(@Path(USER_NAME) userName: String) : Call<GitResponse>

}