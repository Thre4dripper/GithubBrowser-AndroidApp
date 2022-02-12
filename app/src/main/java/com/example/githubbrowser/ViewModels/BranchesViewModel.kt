package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.BranchesRecyclerAdapter

import com.example.githubbrowser.Networking.ApiResponse

class BranchesViewModel {
    companion object {
        lateinit var adapter: BranchesRecyclerAdapter
        var branchesList: MutableList<String> = mutableListOf()

        fun getBranchesList(owner: String, repoName: String):Int {
            branchesList = ApiResponse.getBranchesList(owner, repoName)
            return branchesList.size
        }
    }
}