package iman.mohammadpour.batman.data.repositories.impl

import iman.mohammadpour.batman.data.api.MovieApi
import iman.mohammadpour.batman.data.dao.MovieDao
import iman.mohammadpour.batman.data.repositories.MovieRepository

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao
) : BaseRepository(), MovieRepository {


    override fun search(searchKey: String) = remote(api.search(s = searchKey)) { rsp ->
        rsp?.let {
            dao.save(it.search)
        }
    }

    override fun getMovie(imdbID: String) = remote(api.getMovieDetail(id = imdbID)) { movie ->
        movie?.let {
            dao.save(it)
        }
    }

    override fun findAll() = dao.findAll()

    override fun findById(id: String) = dao.findById(id)
}