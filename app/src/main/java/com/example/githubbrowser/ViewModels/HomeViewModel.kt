package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.Networking.JsonParser
import com.example.githubbrowser.dataModels.RepoItem

class HomeViewModel {
    private val TAG = "HomeViewModel"

    companion object{
        var adapter: ReposRecyclerAdapter
        private var reposList: MutableList<RepoItem> = mutableListOf()

        init {
            adapter= ReposRecyclerAdapter(reposList)
        }

        fun getReposList() {

            reposList.add(JsonParser.getRepoDetails())

            adapter.notifyItemInserted(reposList.size)
        }
    }

}