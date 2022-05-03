package com.paging.quickpage.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paging.quickpage.data.RepositoryItem
import java.lang.Exception

class SearchedRepoPagingSource(
    private val query: String,
    private val sort: String,
    private val order: String,
    private val perPage: Int,
    private val githubRepo: GithubRepo
): PagingSource<Int, RepositoryItem>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItem> {
        val pageNum = params.key ?: 1

        return try {
            val repository = githubRepo.getListOfRepositories(query, sort, order, pageNum, perPage)
            LoadResult.Page(
                data = repository.items,
                prevKey = null,
                nextKey = pageNum + 1)
        } catch (exc: Exception) {
            LoadResult.Error(exc)
        }
    }
}