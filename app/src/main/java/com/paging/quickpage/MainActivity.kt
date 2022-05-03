package com.paging.quickpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.paging.quickpage.adapters.SearchedRepoAdapter
import com.paging.quickpage.factory.SearchRepoViewModelFactory
import com.paging.quickpage.repo.GithubRepo
import com.paging.quickpage.usecase.SearchRepositoriesUseCase
import com.paging.quickpage.viewmodels.SearchRepoViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var mSearchRepoViewModel: SearchRepoViewModel
    private lateinit var mRvRepos: RecyclerView
    private lateinit var mSearchedRepoAdapter: SearchedRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSearchRepoViewModel = ViewModelProvider(
            this,
            SearchRepoViewModelFactory(SearchRepositoriesUseCase(GithubRepo()))
        ).get(SearchRepoViewModel::class.java)

        mRvRepos = findViewById(R.id.rv_repos)
        mSearchedRepoAdapter = SearchedRepoAdapter()
        mRvRepos.adapter = mSearchedRepoAdapter

        observeSearchedRepo()

        fetchRepos()
    }

    private fun fetchRepos() {
        mSearchRepoViewModel.getRepositories(
            query = "tetris+language:assembly",
            sort = "stars",
            order = "desc",
            perPage = 10
        )
    }

    private fun observeSearchedRepo() {

        lifecycleScope.launchWhenStarted {

            mSearchRepoViewModel.searchedRepos.collect { pagingData ->
                pagingData ?: return@collect

                mSearchedRepoAdapter.submitData(pagingData)
            }
        }
    }
}