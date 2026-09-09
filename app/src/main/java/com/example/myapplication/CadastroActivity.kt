package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener { saveMovie() }
        binding.linkViewList.setOnClickListener {
            startActivity(Intent(this, ListaActivity::class.java))
        }
    }

    private fun saveMovie() {
        val title = binding.editMovieTitle.text?.toString()?.trim().orEmpty()
        val genre = binding.editMovieGenre.text?.toString()?.trim().orEmpty()
        val posterUrl = binding.editPosterUrl.text?.toString()?.trim().orEmpty()

        if (title.isEmpty() || genre.isEmpty() || posterUrl.isEmpty()) {
            Toast.makeText(this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show()
            return
        }

        MovieRepository.addMovie(Movie(title, genre, posterUrl))
        Toast.makeText(this, R.string.movie_saved, Toast.LENGTH_SHORT).show()

        binding.editMovieTitle.text?.clear()
        binding.editMovieGenre.text?.clear()
        binding.editPosterUrl.text?.clear()
    }
}
