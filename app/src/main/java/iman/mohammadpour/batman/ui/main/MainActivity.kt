package iman.mohammadpour.batman.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.extensions.hasInternetAccess
import iman.mohammadpour.batman.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    private val vm by viewModel<MainViewModel>()

    private val listLM by lazy {
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }
    private val movieAdapter by lazy { MovieAdapter(this) }


    private val onMoviesObserver = Observer<List<MovieSummary>> {
        movieAdapter.setData(it)
    }
    private val onMoviesErrorObserver = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lst_movies.layoutManager = listLM
        lst_movies.adapter = movieAdapter

        vm.onLiveMovies.observe(this, onMoviesObserver)
        vm.onMoviesError.observe(this, onMoviesErrorObserver)

        vm.onViewCreated(hasInternetAccess())

    }

    override fun onMovieClicked(movie: MovieSummary, img: ImageView, transitionName: String) {

        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.imdbID)
            putExtra(DetailActivity.EXTRA_MOVIE_POSTER, movie.poster)
            putExtra(DetailActivity.EXTRA_MOVIE_POSTER_TRANSITION_NAME, transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity,
                img,
                transitionName
            )
            startActivity(this, options.toBundle());
        }

    }

}