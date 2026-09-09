package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val onListChanged: () -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.textMovieTitle.text = movie.title
        holder.binding.textMovieGenre.text = movie.genre

        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .placeholder(R.drawable.ic_movie)
            .error(R.drawable.ic_movie)
            .into(holder.binding.imagePoster)

        holder.binding.buttonEdit.setOnClickListener {
            Toast.makeText(holder.itemView.context, R.string.edit_not_available, Toast.LENGTH_SHORT).show()
        }

        holder.binding.buttonDelete.setOnClickListener {
            val adapterPosition = holder.bindingAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                movies.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                onListChanged()
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}
