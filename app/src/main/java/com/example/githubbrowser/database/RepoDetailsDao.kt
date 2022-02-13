package com.example.githubbrowser.database

import androidx.room.*

@Dao
interface RepoDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(repoDetailsEntity: RepoDetailsEntity)

    @Query("Select * From repo_details")
    suspend fun getData(): MutableList<RepoDetailsEntity>

    @Delete
    suspend fun deleteData(repoDetailsEntity: RepoDetailsEntity)
}