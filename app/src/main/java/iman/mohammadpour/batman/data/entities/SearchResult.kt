package iman.mohammadpour.batman.data.entities

import com.google.gson.annotations.SerializedName

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

data class SearchResult(

    @SerializedName("Search")
    val search: List<MovieSummary>

)