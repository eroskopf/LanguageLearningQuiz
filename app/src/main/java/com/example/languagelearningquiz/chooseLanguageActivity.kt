package com.example.languagelearningquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.databinding.ActivityChooseLanguageBinding


class chooseLanguageActivity : AppCompatActivity() {

    lateinit var binding: ActivityChooseLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ChineseImageButton.setOnClickListener {
            startActivity(Intent(this, ChineseQuizActivity::class.java))
        }

        binding.SpanishImageButton.setOnClickListener {
            startActivity(Intent(this, SpanishQuizActivity::class.java))
        }





    }
}