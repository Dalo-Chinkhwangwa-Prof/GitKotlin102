package com.britishbroadcast.gitkotlinapplication.model.network

import com.britishbroadcast.gitkotlinapplication.model.data.GitResponse
import com.britishbroadcast.gitkotlinapplication.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRetrofit {

    private val gitService: GitService

    init {
        gitService = createGitService(createGitRetrofit())
    }

    private fun createGitRetrofit(): Retrofit
        = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createGitService(retrofit: Retrofit): GitService
        = retrofit.create(/*GitService.class*/GitService::class.java)

    fun getReposiroties(userName: String): Call<GitResponse> = gitService.getRepositories(userName)
}