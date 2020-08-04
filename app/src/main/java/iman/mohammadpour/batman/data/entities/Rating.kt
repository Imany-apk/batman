package iman.mohammadpour.batman.data.entities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

data class Rating(

    @SerializedName("Source")
    @ColumnInfo(name = "source")
    val source: String,

    @SerializedName("Value")
    @ColumnInfo(name = "value")
    val value: String
)