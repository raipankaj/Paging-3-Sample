package com.paging.quickpage.services

import com.paging.quickpage.data.Repository
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubServices {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Repository
}