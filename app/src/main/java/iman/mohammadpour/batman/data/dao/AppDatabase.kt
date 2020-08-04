package iman.mohammadpour.batman.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import iman.mohammadpour.batman.data.entities.Movie

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

@Database(
    entities = [
        Movie::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}