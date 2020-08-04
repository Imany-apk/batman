package iman.mohammadpour.batman.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.extensions.hasInternetAccess
import iman.mohammadpour.batman.extensions.load
import iman.mohammadpour.batman.utils.SingleToast
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val vm by viewModel<DetailViewModel>()


    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        const val EXTRA_POSTER = "EXTRA_MOVIE_POSTER"
        const val EXTRA_FROM_LOCAL = "EXTRA_FROM_LOCAL"
        const val EXTRA_POSTER_TRANSITION_NAME = "EXTRA_POSTER_TRANSITION_NAME"
        const val EXTRA_TITLE_TRANSITION_NAME = "EXTRA_TITLE_TRANSITION_NAME"
        const val EXTRA_YEAR_TRANSITION_NAME = "EXTRA_YEAR_TRANSITION_NAME"
    }

    private val onMovieDetailRetrieved = Observer<Movie> {
        swipe_refresh.isRefreshing = false
        updateView(it)
    }

    private val onMovieDetailError = Observer<String> {
        swipe_refresh.isRefreshing = false
        SingleToast.show(applicationContext, it, Toast.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vm.onLiveMovie.observe(this, onMovieDetailRetrieved)
        vm.onMovieError.observe(this, onMovieDetailError)

        cns_poster.transitionName = intent?.extras?.getString(EXTRA_POSTER_TRANSITION_NAME)
        txt_title.transitionName = intent?.extras?.getString(EXTRA_TITLE_TRANSITION_NAME)
        txt_year.transitionName = intent?.extras?.getString(EXTRA_YEAR_TRANSITION_NAME)

        intent?.extras?.getString(EXTRA_POSTER)?.let {
            img_poster.load(it)
        }

        val imdbID = intent?.extras?.getString(EXTRA_MOVIE_ID)
        getMovieDetail(imdbID)

        swipe_refresh.setOnRefreshListener {
            getMovieDetail(imdbID)
        }

    }

    private fun getMovieDetail(imdbID: String?) {
        imdbID?.let {
            val fromLocal = intent?.extras?.getBoolean(EXTRA_FROM_LOCAL, false) ?: false
            vm.onViewCreated(imdbID, hasInternetAccess(), fromLocal)
        }
    }

    private fun updateView(movie: Movie) {
        txt_title.text = movie.title
        txt_title.animate().alpha(1f).translationX(0f).duration = 600
        supportActionBar?.title = movie.title
//        txt_title.isSelected = true
        txt_year.text = movie.year
        txt_type.text = movie.type

        txt_director.text = movie.director
        txt_production.text = movie.production
        txt_released.text = movie.released
        txt_duration.text = movie.runtime
        if (movie.isMovie()) {
            txt_box_office.text = movie.boxOffice
        } else {
            txt_box_office_label.text = getString(R.string.seasons)
            txt_box_office.text = movie.totalSeasons
        }

        val na = getString(R.string.not_available)

        txt_language.text =
            getString(R.string.lang_country, movie.language ?: na, movie.country ?: na)
        txt_genre.text = movie.genre
        txt_writer.text = movie.writer
        txt_stars.text = movie.actors
        txt_plot.text = movie.plot
        txt_awards.text = movie.awards

        txt_ratings_imdb.text =
            movie.ratings?.find { it.source == "Internet Movie Database" }?.value ?: na
        txt_ratings_rotten_tomatoes.text =
            movie.ratings?.find { it.source == "Rotten Tomatoes" }?.value ?: na
        txt_ratings_metacritic.text =
            movie.ratings?.find { it.source == "Metacritic" }?.value ?: na

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        txt_title.visibility = View.GONE
        txt_title_shadow.visibility = View.GONE
        super.onBackPressed()
    }

    override fun onPause() {
        swipe_refresh.isRefreshing = false
        super.onPause()
    }

}