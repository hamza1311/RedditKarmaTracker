package com.example.redditkarmatracker.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.redditkarmatracker.models.Karma

@Dao
interface KarmaDao {

    @Query("SELECT * FROM karma ORDER BY id DESC")
    fun getKarma(): LiveData<List<Karma>>

    @Insert
    suspend fun addKarma(karma: Karma)
}