package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.CommitsRecyclerAdapter
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.dataModels.CommitItem

class CommitsViewModel {
    companion object {
        var selectedBranch: Int = -1
        lateinit var commitsAdapter: CommitsRecyclerAdapter

        var commitsList: MutableList<CommitItem>? = mutableListOf()

        fun getCommitsList(
            owner: String,
            repoName: String,
            branch: String
        ): MutableList<CommitItem>? {
            commitsList = ApiResponse.getCommitsList(owner, repoName, branch)
            return commitsList
        }
    }
}