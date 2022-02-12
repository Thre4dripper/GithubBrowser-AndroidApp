package com.example.githubbrowser.ViewModels

import com.example.githubbrowser.Adapters.BranchesRecyclerAdapter
import com.example.githubbrowser.Adapters.IssueRecyclerAdapter

import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.dataModels.IssueItem

class DetailsViewModel {
    companion object {
        var selectedRepo: Int = -1
        lateinit var branchesAdapter: BranchesRecyclerAdapter
        lateinit var issuesAdapter: IssueRecyclerAdapter

        var branchesList: MutableList<String>? = mutableListOf()
        var issuesList: MutableList<IssueItem>? = mutableListOf()

        fun getBranchesList(owner: String, repoName: String): Int? {
            branchesList = ApiResponse.getBranchesList(owner, repoName)

            if (branchesList == null) return null
            return branchesList!!.size
        }

        fun getIssuesList(owner: String, repoName: String): Int? {
            issuesList = ApiResponse.getIssuesList(owner, repoName)

            if (issuesList == null) return null
            return issuesList!!.size
        }
    }
}