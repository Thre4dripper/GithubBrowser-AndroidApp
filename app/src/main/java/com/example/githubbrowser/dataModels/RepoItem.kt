package com.example.githubbrowser.dataModels

data class RepoItem(
    val id:Int,
    val repoOwner:String,
    val repoName: String,
    val repoDesc: String,
    val imgUrl: String,
    val issues:Int
)
