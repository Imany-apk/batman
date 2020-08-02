package iman.mohammadpour.batman.ui.main

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

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VH>() {

    private var movies = listOf<MovieSummary>()


    class VH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieSummary) = with(itemView) {
            img_poster.load(movie.poster)
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