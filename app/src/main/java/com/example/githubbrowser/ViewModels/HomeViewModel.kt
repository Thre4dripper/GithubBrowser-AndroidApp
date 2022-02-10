package com.example.githubbrowser.ViewModels

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.Networking.JsonParser
import com.example.githubbrowser.dataModels.RepoItem
import kotlinx.coroutines.launch

class HomeViewModel{
    private val TAG = "HomeViewModel"

    companion object{
        var adapter: ReposRecyclerAdapter
        var reposList: MutableList<RepoItem> = mutableListOf()

        init {
            adapter= ReposRecyclerAdapter(reposList)
        }

        fun addNewRepo(owner: String, repoName: String) {
            reposList.add(ApiResponse.checkValidity(owner,repoName)!!)
        }
    }

}