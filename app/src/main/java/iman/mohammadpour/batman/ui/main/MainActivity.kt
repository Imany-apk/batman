package iman.mohammadpour.batman.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.extensions.gone
import iman.mohammadpour.batman.extensions.hasInternetAccess
import iman.mohammadpour.batman.extensions.visible
import iman.mohammadpour.batman.ui.detail.DetailActivity
import iman.mohammadpour.batman.utils.SingleToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    private val vm by viewModel<MainViewModel>()

    private val listLM by lazy {
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }
    private val movieAdapter by lazy { MovieAdapter(this) }


    private val onMoviesObserver = Observer<List<MovieSummary>> {
        swipe_refresh.isRefreshing = false
        img_logo.gone()
        txt_no_content.gone()
        lst_movies.visible()
        movieAdapter.setData(it)
    }
    private val onMoviesErrorObserver = Observer<String> {
        swipe_refresh.isRefreshing = false
        img_logo.visible()
        txt_no_content.visible()
        lst_movies.gone()
        SingleToast.show(applicationContext, it, Toast.LENGTH_SHORT)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lst_movies.layoutManager = listLM
        lst_movies.adapter = movieAdapter

        vm.onLiveMovies.observe(this, onMoviesObserver)
        vm.onMoviesError.observe(this, onMoviesErrorObserver)

        vm.onViewCreated(hasInternetAccess())

        swipe_refresh.setOnRefreshListener {
            vm.onViewCreated(hasInternetAccess())
        }

    }

    override fun onMovieClicked(movie: MovieSummary, vararg views: View) {

        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.imdbID)
            putExtra(DetailActivity.EXTRA_POSTER, movie.poster)
            putExtra(DetailActivity.EXTRA_FROM_LOCAL, movie.fetchedBefore)
            putExtra(DetailActivity.EXTRA_POSTER_TRANSITION_NAME, views[0].transitionName)
            putExtra(DetailActivity.EXTRA_TITLE_TRANSITION_NAME, views[1].transitionName)
            putExtra(DetailActivity.EXTRA_YEAR_TRANSITION_NAME, views[2].transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity,
                *(views.map { Pair.create(it, it.transitionName) }.toTypedArray())
            )
            startActivity(this, options.toBundle());
        }

    }

    override fun onPause() {
        SingleToast.cancel()
        super.onPause()
    }

}