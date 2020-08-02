package iman.mohammadpour.batman.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import iman.mohammadpour.batman.data.entities.converters.RatingConverter

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

@Entity(tableName = "movies")
@TypeConverters(
    RatingConverter::class
)
data class Movie(

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

    @SerializedName("Rated")
    @ColumnInfo(name = "rated")
    var rated: String?,

    @SerializedName("Released")
    @ColumnInfo(name = "released")
    var released: String?,

    @SerializedName("Runtime")
    @ColumnInfo(name = "runtime")
    var runtime: String?,

    @SerializedName("Genre")
    @ColumnInfo(name = "genre")
    var genre: String?,

    @SerializedName("Director")
    @ColumnInfo(name = "director")
    var director: String?,

    @SerializedName("Writer")
    @ColumnInfo(name = "writer")
    var writer: String?,

    @SerializedName("Actors")
    @ColumnInfo(name = "actors")
    var actors: String?,

    @SerializedName("Plot")
    @ColumnInfo(name = "plot")
    var plot: String?,

    @SerializedName("Language")
    @ColumnInfo(name = "language")
    var language: String?,

    @SerializedName("Country")
    @ColumnInfo(name = "country")
    var country: String?,

    @SerializedName("Awards")
    @ColumnInfo(name = "awards")
    var awards: String?,

    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    val poster: String,

    @SerializedName("Ratings")
    @ColumnInfo(name = "ratings")
    var ratings: List<Rating>?, // has converter

    @SerializedName("Metascore")
    @ColumnInfo(name = "metascore")
    var metascore: String?,

    @SerializedName("imdbRating")
    @ColumnInfo(name = "imdb_rating")
    var imdbRating: String?,

    @SerializedName("imdbVotes")
    @ColumnInfo(name = "imdb_votes")
    var imdbVotes: String?,

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    val type: String,

    @SerializedName("DVD")
    @ColumnInfo(name = "dvd")
    var dvd: String?,

    @SerializedName("BoxOffice")
    @ColumnInfo(name = "box_office")
    var boxOffice: String?,

    @SerializedName("Production")
    @ColumnInfo(name = "production")
    var production: String?,

    @SerializedName("Website")
    @ColumnInfo(name = "website")
    var website: String?,

    @SerializedName("Response")
    @ColumnInfo(name = "response")
    var response: String?
) : Rsp()