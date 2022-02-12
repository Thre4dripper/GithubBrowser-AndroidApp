package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.BranchesRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse

class CommitsViewModel {
    companion object {
        var selectedBranch: Int = -1
        lateinit var commitsAdapter: BranchesRecyclerAdapter

        var commitsList: MutableList<String> = mutableListOf()

        fun getCommitsList(owner: String, repoName: String): Int {
            commitsList = ApiResponse.getBranchesList(owner, repoName)
            return commitsList.size
        }
    }
}