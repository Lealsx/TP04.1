package com.example.myapplication

object MovieRepository {

    val movies = mutableListOf<Movie>()

    fun addMovie(movie: Movie) {
        movies.add(movie)
    }
}
