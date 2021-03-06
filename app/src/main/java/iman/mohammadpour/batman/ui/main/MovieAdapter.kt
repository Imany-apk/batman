package iman.mohammadpour.batman.ui.main

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.extensions.load
import kotlinx.android.synthetic.main.adapter_movie.view.*

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class MovieAdapter(
    private val listener: MovieItemClickListener
) : RecyclerView.Adapter<MovieAdapter.VH>() {

    private var movies = listOf<MovieSummary>()
    private var lastClickTime = 0L

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieSummary) = with(itemView) {
            img_poster.load(movie.poster)
            img_poster.transitionName = "posterTransitionName#${movie.imdbID}"
            txt_title.text = movie.title
            txt_title.transitionName = "titleTransitionName#${movie.imdbID}"
            txt_title.isSelected = true
            txt_year.text = movie.year
            txt_year.transitionName = "yearTransitionName#${movie.imdbID}"

            setOnClickListener {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000)
                    return@setOnClickListener

                lastClickTime = SystemClock.elapsedRealtime()

                listener.onMovieClicked(movie, img_poster, txt_title, txt_year)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(movies[position])
    }

    fun setData(data: List<MovieSummary>) {
        movies = data
        notifyDataSetChanged()
    }
}