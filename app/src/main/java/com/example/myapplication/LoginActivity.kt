package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        private const val VALID_USERNAME = "admin"
        private const val VALID_PASSWORD = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        val username = binding.editUsername.text?.toString()?.trim().orEmpty()
        val password = binding.editPassword.text?.toString()?.trim().orEmpty()

        if (username == VALID_USERNAME && password == VALID_PASSWORD) {
            binding.textLoginError.visibility = android.view.View.GONE
            startActivity(Intent(this, CadastroActivity::class.java))
            finish()
        } else {
            binding.textLoginError.visibility = android.view.View.VISIBLE
        }
    }
}
