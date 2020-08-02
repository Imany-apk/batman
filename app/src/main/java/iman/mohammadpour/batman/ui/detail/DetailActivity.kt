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
        const val EXTRA_MOVIE_POSTER = "EXTRA_MOVIE_POSTER"
        const val EXTRA_MOVIE_POSTER_TRANSITION_NAME = "EXTRA_MOVIE_POSTER_TRANSITION_NAME"
    }

    private val onMovieDetailRetrieved = Observer<Movie> {
        updateView(it)
    }

    private val onMovieDetailError = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vm.onLiveMovie.observe(this, onMovieDetailRetrieved)
        vm.onMovieError.observe(this, onMovieDetailError)

        intent?.extras?.getString(EXTRA_MOVIE_POSTER_TRANSITION_NAME)?.let {
            img_poster.transitionName = it
        } ?: finish()

        intent?.extras?.getString(EXTRA_MOVIE_POSTER)?.let {
            img_poster.load(it)
        } ?: finish()

        intent?.extras?.getString(EXTRA_MOVIE_ID)?.let {
            vm.onViewCreated(it, hasInternetAccess())
        } ?: finish()

    }

    private fun updateView(movie: Movie) {
        txt_title.text = movie.title
        supportActionBar?.title = movie.title
        txt_title.isSelected = true
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