package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogAddMovieBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val movies = mutableListOf(
        Movie("O Poderoso Chefão", "Francis Ford Coppola"),
        Movie("Interestelar", "Christopher Nolan"),
        Movie("Cidade de Deus", "Fernando Meirelles"),
        Movie("Parasita", "Bong Joon-ho")
    )

    private var isGridLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapter = MovieAdapter(movies)
        binding.recyclerViewMovies.adapter = adapter
        applyLayoutManager()
        updateEmptyState()

        binding.fabAddMovie.setOnClickListener { showAddMovieDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_toggle_layout) {
            isGridLayout = !isGridLayout
            applyLayoutManager()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun applyLayoutManager() {
        binding.recyclerViewMovies.layoutManager = if (isGridLayout) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
    }

    private fun showAddMovieDialog() {
        val dialogBinding = DialogAddMovieBinding.inflate(layoutInflater)

        AlertDialog.Builder(this)
            .setTitle(R.string.add_movie)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                val title = dialogBinding.editMovieTitle.text?.toString()?.trim().orEmpty()
                val director = dialogBinding.editMovieDirector.text?.toString()?.trim().orEmpty()

                if (title.isEmpty() || director.isEmpty()) {
                    Toast.makeText(this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show()
                } else {
                    adapter.addMovie(Movie(title, director))
                    binding.recyclerViewMovies.scrollToPosition(adapter.itemCount - 1)
                    updateEmptyState()
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun updateEmptyState() {
        binding.textEmpty.visibility =
            if (movies.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
    }
}
