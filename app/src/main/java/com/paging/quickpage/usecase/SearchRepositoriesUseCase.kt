package com.paging.quickpage.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paging.quickpage.data.RepositoryItem
import com.paging.quickpage.repo.GithubRepo
import com.paging.quickpage.repo.SearchedRepoPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * A use case to fetch the repositories from Github based
 * on the query where repositories are aligned based on
 * the specified order
 *
 * @param query: Fetch repos based on the query
 * @param order: Fetch repos based on order - ASC or DESC
 *
 */
class SearchRepositoriesUseCase(private val githubRepo: GithubRepo) {

    operator fun invoke(
        query: String, sort: String,
        order: String, perPage: Int,
        viewModelScope: CoroutineScope,
    ): Flow<PagingData<RepositoryItem>> {

        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1),
            pagingSourceFactory = {
                SearchedRepoPagingSource(
                    query, sort, order, perPage, githubRepo
                )
            }
        ).flow.cachedIn(viewModelScope)
    }
}