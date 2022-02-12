package com.example.githubbrowser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepoDetailsEntity::class], version = 1)
abstract class RepoDetailsDatabase : RoomDatabase() {

    abstract fun entityDao(): RepoDetailsDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDetailsDatabase? = null

        fun getDatabase(context: Context): RepoDetailsDatabase {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RepoDetailsDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}