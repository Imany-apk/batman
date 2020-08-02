package iman.mohammadpour.batman.data.entities.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import iman.mohammadpour.batman.data.entities.Rating

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

object RatingConverter {

    private val gSon by lazy { Gson() }

    @TypeConverter
    @JvmStatic
    fun toList(json: String?): List<Rating>? {
        val type = object : TypeToken<List<Rating>?>() {}.type
        return gSon.fromJson(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<Rating>?): String? {
        val type = object : TypeToken<List<Rating>?>() {}.type
        return gSon.toJson(list, type)
    }

}