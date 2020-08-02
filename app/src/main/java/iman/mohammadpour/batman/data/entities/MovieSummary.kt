package iman.mohammadpour.batman.data.entities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */
data class MovieSummary(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Long = 1,

    @SerializedName("imdbID")
    @ColumnInfo(name = "imdb_id")
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
    val poster: String,

    @ColumnInfo(name = "fetched_before")
    var fetchedBefore: Boolean = false
)