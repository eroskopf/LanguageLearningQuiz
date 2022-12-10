package com.example.languagelearningquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.data.LanguageUser
import com.example.languagelearningquiz.databinding.ActivityChooseLanguageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlin.math.max


class chooseLanguageActivity : AppCompatActivity() {

    lateinit var binding: ActivityChooseLanguageBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val db = FirebaseFirestore.getInstance()
        val currentUser = auth.currentUser
        val id = currentUser?.uid

        binding.ChineseImageButton.setOnClickListener {
            val userDocRef = db
                .collection("languageusers")
                .whereEqualTo("uid", id)
                .get().addOnSuccessListener {
                    val langUser = it.documents.get(0).toObject(LanguageUser::class.java)
                    val updatedUser = LanguageUser(
                        langUser!!.displayName,
                        "Chinese",
                        langUser!!.ChScore,
                        langUser.SpScore,
                        id!!,
                    )
                    db.collection("languageusers").document(id!!).set(updatedUser)
                }
            startActivity(Intent(this, ChineseQuizActivity::class.java))
        }

        binding.SpanishImageButton.setOnClickListener {
            val userDocRef = db
                .collection("languageusers")
                .whereEqualTo("uid", id)
                .get().addOnSuccessListener {
                    val langUser = it.documents.get(0).toObject(LanguageUser::class.java)
                    val updatedUser = LanguageUser(
                        langUser!!.displayName,
                        "Spanish",
                        langUser!!.ChScore,
                        langUser.SpScore,
                        id!!,
                    )
                    db.collection("languageusers").document(id!!).set(updatedUser)
                }
            startActivity(Intent(this, SpanishQuizActivity::class.java))
        }





    }
}