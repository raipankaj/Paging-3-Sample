package com.paging.quickpage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.paging.quickpage.R
import com.paging.quickpage.data.RepositoryItem

class SearchedRepoAdapter : PagingDataAdapter<RepositoryItem, SearchedRepoAdapter.SearchedRepoViewHolder>(RepoItemDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedRepoViewHolder {
        return SearchedRepoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_repo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchedRepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class SearchedRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvFullName: TextView = itemView.findViewById(R.id.tv_full_name)
        private val tvDesc: TextView = itemView.findViewById(R.id.tv_desc)
        private val tvLicenseKey: TextView = itemView.findViewById(R.id.tv_license_key)
        private val tvLicenseName: TextView = itemView.findViewById(R.id.tv_license_name)

        fun onBind(repositoryItem: RepositoryItem?) {
            repositoryItem?.let {
                tvName.text = repositoryItem.name
                tvFullName.text = repositoryItem.fullName
                tvDesc.text = repositoryItem.description
                tvLicenseKey.text = repositoryItem.license?.key ?: "Not Available"
                tvLicenseName.text = repositoryItem.license?.name ?: "Not Available"
            }
        }
    }

    object RepoItemDiffUtil : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem == newItem
        }

    }
}