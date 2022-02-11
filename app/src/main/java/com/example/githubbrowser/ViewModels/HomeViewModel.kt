package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.dataModels.RepoItem

class HomeViewModel {
    private val TAG = "HomeViewModel"

    companion object {
        var adapter: ReposRecyclerAdapter
        var reposList: MutableList<RepoItem> = mutableListOf()

        init {
            adapter = ReposRecyclerAdapter(reposList)
        }

        fun addNewRepo(owner: String, repoName: String): RepoItem? {
            val repoItem = ApiResponse.getJsonResponse(owner, repoName)

            //checking result of API response recieved
            if (repoItem != null)
                reposList.add(repoItem)
            return repoItem
        }
    }

}