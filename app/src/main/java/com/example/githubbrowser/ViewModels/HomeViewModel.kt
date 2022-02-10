package com.example.githubbrowser.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.Networking.JsonParser
import com.example.githubbrowser.dataModels.RepoItem

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    var adapter: ReposRecyclerAdapter
    private var reposList: MutableList<RepoItem> = mutableListOf()

    init {
        adapter= ReposRecyclerAdapter(reposList)
    }

    fun getReposList(rc: RecyclerView) {

        reposList.add(JsonParser.getRepoDetails())

        adapter.notifyItemInserted(reposList.size)
        rc.scrollToPosition(reposList.size-1)
    }
}