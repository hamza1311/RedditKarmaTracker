package com.example.redditkarmatracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Karma")
data class Karma(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val primaryKey: Int = 0,
    @ColumnInfo(name = "current_karma") val currentKarma: Int,
    @ColumnInfo(name = "current_time") val currentTime: Long
)