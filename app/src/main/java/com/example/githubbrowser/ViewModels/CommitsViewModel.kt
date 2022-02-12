package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.BranchesRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.dataModels.CommitItem

class CommitsViewModel {
    companion object {
        var selectedBranch: Int = -1
        lateinit var commitsAdapter: BranchesRecyclerAdapter

        var commitsList: MutableList<CommitItem> = mutableListOf()

        fun getCommitsList(owner: String, repoName: String): MutableList<CommitItem> {
            commitsList = ApiResponse.getCommitsList(owner, repoName)
            return commitsList
        }
    }
}