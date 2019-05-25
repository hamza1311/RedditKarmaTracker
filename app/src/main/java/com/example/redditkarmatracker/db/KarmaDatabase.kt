package com.example.redditkarmatracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redditkarmatracker.models.Karma

@Database(entities = [Karma::class], version = 1)
abstract class KarmaDatabase : RoomDatabase() {
    abstract fun karmaDao(): KarmaDao

    companion object {
        private var INSTANCE: KarmaDatabase? = null

        fun getDatabase(context: Context): KarmaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, KarmaDatabase::class.java, "karma_database")
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}