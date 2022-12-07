package com.example.languagelearningquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.databinding.ActivityLoginBinding
import com.example.languagelearningquiz.databinding.ActivityScoreBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}