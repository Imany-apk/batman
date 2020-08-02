package iman.mohammadpour.batman.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.data.entities.MovieSummary

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Movie::class)
    fun save(movies: List<MovieSummary>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: Movie)

    @Query("SELECT * FROM movies")
    fun findAll(): LiveData<List<MovieSummary>>

    @Query("SELECT * FROM movies WHERE imdb_id=:id")
    fun findById(id: String): LiveData<Movie>

}