package iman.mohammadpour.batman.ui.main

import android.view.View
import iman.mohammadpour.batman.data.entities.MovieSummary

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

interface MovieItemClickListener {

    fun onMovieClicked(movie: MovieSummary, vararg views: View)

}