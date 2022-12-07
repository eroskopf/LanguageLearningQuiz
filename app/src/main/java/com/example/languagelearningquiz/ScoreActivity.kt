package com.example.languagelearningquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.databinding.ActivityScoreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ScoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityScoreBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //should get fed in intent as the score from the quiz


    }

    override fun onStart() {
        super.onStart()

        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        val id = currentUser?.uid

        val doc = FirebaseFirestore.getInstance()
            .collection("languageusers")
            .whereEqualTo("uid", id)
            .get()




    }
}