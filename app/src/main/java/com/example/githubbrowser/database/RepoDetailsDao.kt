package com.example.githubbrowser.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(repoDetailsEntity: RepoDetailsEntity)

    @Query("Select * From repo_details")
    suspend fun getdata(): List<RepoDetailsEntity>
}