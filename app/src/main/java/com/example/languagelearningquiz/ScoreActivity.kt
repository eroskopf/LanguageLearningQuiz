package com.example.languagelearningquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.data.LanguageUser
import com.example.languagelearningquiz.databinding.ActivityScoreBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlin.math.max

class ScoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityScoreBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var language: String
    private lateinit var adapter: ScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newScore = intent.getIntExtra("score", 0)
        //check to see if new score is high score
        setAttributes(newScore)


        initRecyclerView()

        //display the scoreboards
        getList()

    }


    private fun setAttributes(newScore: Int) {
        auth = Firebase.auth
        val db = FirebaseFirestore.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        val id = currentUser?.uid
        var chScore: Int
        var spScore: Int

        val userDocRef = db
            .collection("languageusers")
            .whereEqualTo("uid", id)
            .get().addOnSuccessListener {
                val langUser = it.documents.get(0).toObject(LanguageUser::class.java)
                chScore = langUser!!.ChScore
                spScore = langUser!!.SpScore
                language = langUser!!.language
                //update max score
                if (language == "Chinese") {
                    chScore = max(chScore, newScore)
                }
                else {
                    spScore = max(spScore, newScore)
                }
                val updatedUser = LanguageUser(
                    langUser!!.displayName,
                    language,
                    chScore,
                    spScore,
                    id!!,
                )
                db.collection("languageusers").document(id!!).set(updatedUser)
                //db.collection("languageusers").document("LA").set(created new user object)
            }
    }

    private fun getList() {
        //will display the current language scoreboard
        val db = FirebaseFirestore.getInstance()
        if (language == "Chinese") {
            val userDocRef = db
                .collection("languageusers")
                .whereEqualTo("language", language)
                .orderBy("ChScore", Query.Direction.DESCENDING)
                .get().addOnSuccessListener {
                    val length = it.documents.size
                    var langUser: LanguageUser
                    for (i in 0 until length) {
                        langUser = it.documents.get(i).toObject(LanguageUser::class.java)!!
                        userCreated(langUser)
                        //will add in order
                    }
                }
        } else {
            val userDocRef = db
                .collection("languageusers")
                .whereEqualTo("language", language)
                .orderBy("SpScore", Query.Direction.DESCENDING)
                .get().addOnSuccessListener {
                    val length = it.documents.size
                    var langUser: LanguageUser
                    for (i in 0 until length) {
                        langUser = it.documents.get(i).toObject(LanguageUser::class.java)!!
                        userCreated(langUser)
                        //will add in order
                    }
                }
        }
    }

    private fun initRecyclerView() {
        adapter = ScoreAdapter(this)
        binding.recyclerScores.adapter = adapter
    }

    private fun userCreated(user: LanguageUser) {
        adapter.addUser(user)
    }
}