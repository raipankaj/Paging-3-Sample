package com.paging.quickpage.repo

import com.paging.quickpage.data.Repository
import com.paging.quickpage.network.RetrofitClient

class GithubRepo {

    /**
     * Get the list of repositories based on the query parameter
     * and order specified.
     * It also supports pagination with the page property.
     */
    suspend fun getListOfRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    ): Repository {
        return RetrofitClient.service.getRepositories(
            query, sort, order, page, perPage
        )
    }

}