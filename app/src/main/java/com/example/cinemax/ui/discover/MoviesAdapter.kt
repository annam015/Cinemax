package com.example.cinemax.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cinemax.data.Movie
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemax.R
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movie_title)
        val overview: TextView = view.findViewById(R.id.movie_overview)
        val poster: ImageView = view.findViewById(R.id.movie_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview
        Picasso.get().load(movie.posterPath).into(holder.poster)
    }

    override fun getItemCount() = movies.size
}