package iman.mohammadpour.batman.data.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

data class MovieSummary(

    @SerializedName("imdbID")
    @ColumnInfo(name = "imdb_id")
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,

    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("Year")
    @ColumnInfo(name = "year")
    val year: String,

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    val type: String,

    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    val poster: String
)