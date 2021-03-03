package com.britishbroadcast.gitkotlinapplication.presenter

import com.britishbroadcast.gitkotlinapplication.model.data.GitResponse
import com.britishbroadcast.gitkotlinapplication.model.network.GitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppPresenter(val gitView: GitContract.GitView): GitContract.GitPresenter {

    private val gitRetrofit = GitRetrofit()

//    private var gitRetrofit2: GitRetrofit? = null

    override fun getRepositories(userName: String) {

        Thread{
            gitRetrofit.getReposiroties(userName)
                .enqueue(object: Callback<GitResponse>{
                    override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                        gitView.displayError("An error occurred: ${t.localizedMessage}")
                    }

                    override fun onResponse(
                        call: Call<GitResponse>,
                        response: Response<GitResponse>
                    ) {
                       //Scope functions: let, apply, run, with
                        response.body()?.let {
                            // the let block will not run if the body() is null
                            //whatever is in here is not null
                            if(it.isNotEmpty())
                                gitView.displayRepositories(it)
                            else
                                gitView.displayError("No results found.")

                        } ?: gitView.displayError("Response was null.")
                    }
                })

            //in java
//                .enqueue(new Callback{...})
        }.start()
    }
}