package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.dataModels.RepoItem

class HomeViewModel{
    private val TAG = "HomeViewModel"

    companion object {
        lateinit var adapter: ReposRecyclerAdapter
        var reposList: MutableList<RepoItem> = mutableListOf()

        fun addNewRepo(owner: String, repoName: String): RepoItem? {
            val repoItem = ApiResponse.getRepoInfo(owner, repoName)

            //checking result of API response received
            if (repoItem != null)
                reposList.add(repoItem)
            return repoItem
        }
    }

}