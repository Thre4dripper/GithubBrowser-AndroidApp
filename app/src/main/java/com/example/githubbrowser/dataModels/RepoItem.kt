package com.example.githubbrowser.dataModels

data class RepoItem(
    val repoOwner:String,
    val repoName: String,
    val repoDesc: String,
    val imgUrl: String,
    val issues:Int
)
