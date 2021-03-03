package com.britishbroadcast.gitkotlinapplication.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.britishbroadcast.gitkotlinapplication.databinding.ActivityMainBinding
import com.britishbroadcast.gitkotlinapplication.model.data.GitResponseItem
import com.britishbroadcast.gitkotlinapplication.presenter.AppPresenter
import com.britishbroadcast.gitkotlinapplication.presenter.GitContract
import com.britishbroadcast.gitkotlinapplication.view.adapter.GitRepositoryAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), GitContract.GitView {
    //lateinit can only be var
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var presenter: GitContract.GitPresenter

    private var gitAdapter = GitRepositoryAdapter(mutableListOf())//instantiated an empty list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        presenter = AppPresenter(this)

        viewBinding.gitRecyclerview.adapter = gitAdapter

        viewBinding.searchButton.setOnClickListener {
            val userName = viewBinding.searchEdittext.text.toString().trim()
            viewBinding.searchEdittext.text.clear()
            presenter.getRepositories(userName)
            viewBinding.loadingProgressbar.visibility = View.VISIBLE
        }
    }

    override fun displayRepositories(gitList: List<GitResponseItem>) {
        runOnUiThread {
            viewBinding.emptyTextview.visibility = View.GONE
            viewBinding.loadingProgressbar.visibility = View.GONE
            gitAdapter.updateGitRepositories(gitList)
        }
    }

    override fun displayError(errorMessage: String) {
        runOnUiThread {
            viewBinding.loadingProgressbar.visibility = View.GONE
            Snackbar.make(viewBinding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
        }
    }
}