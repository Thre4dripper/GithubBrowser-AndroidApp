package com.example.githubbrowser.dataModels

data class CommitItem(
    val date: String,
    val shaCode: String,
    val commitMessage: String,
    val imgUrl: String,
    val committerName: String
)
