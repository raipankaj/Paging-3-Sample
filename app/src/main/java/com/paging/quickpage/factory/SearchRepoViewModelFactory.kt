package com.paging.quickpage.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paging.quickpage.usecase.SearchRepositoriesUseCase
import com.paging.quickpage.viewmodels.SearchRepoViewModel
import java.lang.IllegalArgumentException

class SearchRepoViewModelFactory(private val searchRepositoriesUseCase: SearchRepositoriesUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRepoViewModel::class.java))
            return SearchRepoViewModel(searchRepositoriesUseCase) as T

        throw IllegalArgumentException("Not the correct viewmodel class")
    }
}