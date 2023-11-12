package com.haker.hakermovies.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haker.hakermovies.MediaTypeEnum
import java.util.Calendar

@Entity(tableName = "favorites")
data class Favorite (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    var imageFilePath: String,
    val poster: String,
    val voteAverage: Double,
    val type: MediaTypeEnum,
    val addDate: String = Calendar.getInstance().time.toString()
)