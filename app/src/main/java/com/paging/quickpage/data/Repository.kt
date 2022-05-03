package com.paging.quickpage.data

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,
    val items: List<RepositoryItem>
)

data class RepositoryItem(
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val owner: RepositoryItemOwner,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    @SerializedName("forks_url")
    val forksUrl: String,
    val license: RepositoryItemLicense?,
    val visibility: String
)

data class RepositoryItemOwner(
    val id: String,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    val type: String
)

data class RepositoryItemLicense(
    val key: String,
    val name: String,
    @SerializedName("spdx_id")
    val packageDataExcId: String,
    val url: String,
    @SerializedName("node_id")
    val nodeId: String
)