package com.example.languagelearningquiz

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.languagelearningquiz.databinding.ActivityChineseQuizBinding

class ChineseQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChineseQuizBinding

    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChineseQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.submitbtn.setOnClickListener {
            score = 0
            calculateScore()
            startActivity(Intent(this, ScoreActivity::class.java))
        }
    }



    fun calculateScore() {
        val answer1 = binding.q1.selectedItemPosition
        val answer2 = binding.q2.selectedItemPosition
        val answer3 = binding.q3.selectedItemPosition
        val answer4 = binding.q4.selectedItemPosition
        val answer5 = binding.q5.selectedItemPosition
        val answer6 = binding.q6.selectedItemPosition
        val answer7 = binding.q7.selectedItemPosition
        val answer8 = binding.q8.selectedItemPosition
        val answer9 = binding.q9.selectedItemPosition
        val answer10 = binding.q10.selectedItemPosition

        when (answer1) {
            1 -> {score+=1}
        }
        when (answer2) {
            2 -> {score+=1}
        }
        when (answer3) {
            3 -> {score+=1}
        }
        when (answer4) {
            3 -> {score+=1}
        }
        when (answer5) {
            0 -> {score+=1}
        }
        when (answer6) {
            3 -> {score+=1}
        }
        when (answer7) {
            1 -> {score+=1}
        }
        when (answer8) {
            2 -> {score+=1}
        }
        when (answer9) {
            2 -> {score+=1}
        }
        when (answer10) {
            1 -> {score+=1}
        }

      //  binding.testScore.text = score.toString()



    }

}