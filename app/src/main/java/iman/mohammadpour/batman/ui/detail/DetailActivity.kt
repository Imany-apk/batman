package iman.mohammadpour.batman.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.extensions.hasInternetAccess
import iman.mohammadpour.batman.extensions.load
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val vm by viewModel<DetailViewModel>()


    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        const val EXTRA_POSTER = "EXTRA_MOVIE_POSTER"
        const val EXTRA_FROM_LOCAL = "EXTRA_FROM_LOCAL"
        const val EXTRA_POSTER_TRANSITION_NAME = "EXTRA_POSTER_TRANSITION_NAME"
    }

    private val onMovieDetailRetrieved = Observer<Movie> {
        updateView(it)
    }

    private val onMovieDetailError = Observer<String> {
        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vm.onLiveMovie.observe(this, onMovieDetailRetrieved)
        vm.onMovieError.observe(this, onMovieDetailError)

        intent?.extras?.getString(EXTRA_POSTER_TRANSITION_NAME)?.let {
            cns_poster.transitionName = it
        } ?: finish()

        intent?.extras?.getString(EXTRA_POSTER)?.let {
            img_poster.load(it)
        } ?: finish()

        intent?.extras?.getString(EXTRA_MOVIE_ID)?.let {
            val fromLocal = intent?.extras?.getBoolean(EXTRA_FROM_LOCAL, false) ?: false
            vm.onViewCreated(it, hasInternetAccess(), fromLocal)
        } ?: finish()

    }

    private fun updateView(movie: Movie) {
        txt_title.text = movie.title
        txt_title.animate().alpha(1f).translationX(0f).duration = 600
        supportActionBar?.title = movie.title
//        txt_title.isSelected = true
        txt_year.text = movie.year
        txt_type.text = movie.type

        txt_director.text = movie.director
        txt_released.text = movie.released
        txt_duration.text = movie.runtime
        txt_genre.text = movie.genre
        txt_writer.text = movie.writer
        txt_stars.text = movie.actors

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

}