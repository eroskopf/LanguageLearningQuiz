package com.example.languagelearningquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.data.LanguageUser
import com.example.languagelearningquiz.databinding.ActivityScoreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ScoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityScoreBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        language = intent.getStringExtra("language").toString()
        //Get chinese scoreboard

        //get spanish scoreboard

    }


    private fun setAttributes() {
        auth = Firebase.auth
        val db = FirebaseFirestore.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        val id = currentUser?.uid
        var score = 0

        val userDocRef = db
            .collection("languageusers")
            .whereEqualTo("uid", id)
            .get().addOnSuccessListener {
                val langUser = it.documents.get(0).toObject(LanguageUser::class.java)
                score = langUser!!.score
            }


        //setting things
        //db.collection("languageusers").document("LA").set(created new user object)
    }
}