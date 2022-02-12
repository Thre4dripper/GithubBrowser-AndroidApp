package com.example.githubbrowser.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_details")
data class RepoDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val repoOwner:String,
    val repoName: String,
    val repoDesc: String,
    val imgUrl: String,
    val issues:Int
)
