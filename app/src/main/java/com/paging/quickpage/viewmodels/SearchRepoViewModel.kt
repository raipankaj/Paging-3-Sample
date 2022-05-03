package com.paging.quickpage.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.paging.quickpage.data.Repository
import com.paging.quickpage.data.RepositoryItem
import com.paging.quickpage.usecase.SearchRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchRepoViewModel(private val searchRepositoriesUseCase: SearchRepositoriesUseCase) :
    ViewModel() {

    private val _searchedRepos = MutableStateFlow<PagingData<RepositoryItem>?>(null)
    val searchedRepos = _searchedRepos.asStateFlow()

    fun getRepositories(
        query: String,
        sort: String, order: String,
        perPage: Int
    ) {
        viewModelScope.launch {
            searchRepositoriesUseCase.invoke(query, sort, order, perPage, viewModelScope)
                .collectLatest { pagingData ->
                    _searchedRepos.value = pagingData
                }
        }
    }
}