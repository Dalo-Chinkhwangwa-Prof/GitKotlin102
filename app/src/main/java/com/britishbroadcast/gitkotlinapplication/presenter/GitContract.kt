package com.britishbroadcast.gitkotlinapplication.presenter

import com.britishbroadcast.gitkotlinapplication.model.data.GitResponseItem

interface GitContract {

    interface GitPresenter{
        fun getRepositories(userName: String)
    }
    interface GitView{
        fun displayRepositories(gitList: List<GitResponseItem>)
        fun displayError(errorMessage: String)
    }

}