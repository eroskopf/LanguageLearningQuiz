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
    private lateinit var adapter: ScoreAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        val newScore = intent.getIntExtra("score", 0)
        //check to see if new score is high score
        setAttributes(newScore)
        //display the scoreboards

    }


    private fun setAttributes(newScore: Int) {
        auth = Firebase.auth
        val db = FirebaseFirestore.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        val id = currentUser?.uid
        var chScore: Int
        var spScore: Int
        var lang: String
        val userDocRef = db
            .collection("languageusers")
            .whereEqualTo("uid", id)
            .get().addOnSuccessListener {
                val langUser = it.documents.get(0).toObject(LanguageUser::class.java)
                val docname = it.documents.get(0).id
                chScore = langUser!!.ChScore
                spScore = langUser!!.SpScore
                lang = langUser!!.language
                //update max score
                if (lang == "Chinese") {
                    chScore = max(chScore, newScore)
                }
                else {
                    spScore = max(spScore, newScore)
                }
                val updatedUser = LanguageUser(
                    langUser!!.displayName,
                    lang,
                    chScore,
                    spScore,
                    id!!,
                )
                db.collection("languageusers").document(docname).set(updatedUser)
                //db.collection("languageusers").document("LA").set(created new user object)
                getList(lang)
            }
    }

    private fun getList(l: String) {
        //will display the current language scoreboard
        val db = FirebaseFirestore.getInstance()
        if (l == "Chinese") {
            binding.tvScoreboardTitle.text = getString(R.string.chinese_scoreboard)
            val userDocRef = db
                .collection("languageusers")
                .orderBy("chScore", Query.Direction.DESCENDING)
                .get().addOnSuccessListener {
                    val length = it.documents.size
                    var langUser: LanguageUser
                    for (i in 0 until length) {
                        langUser = it.documents.get(i).toObject(LanguageUser::class.java)!!
                        userCreated(langUser, l)
                        //will add in order
                    }
                }
        } else if (l == "Spanish") {
            binding.tvScoreboardTitle.text = getString(R.string.spanish_scoreboard)
            val userDocRef = db
                .collection("languageusers")
                .orderBy("spScore", Query.Direction.DESCENDING)
                .get().addOnSuccessListener {
                    val length = it.documents.size
                    var langUser: LanguageUser
                    for (i in 0 until length) {
                        langUser = it.documents.get(i).toObject(LanguageUser::class.java)!!
                        userCreated(langUser, l)
                        //will add in order
                    }
                }
        }

    }

    private fun initRecyclerView() {
        adapter = ScoreAdapter(this)
        binding.recyclerScores.adapter = adapter
    }

    private fun userCreated(user: LanguageUser, language: String) {
        adapter.addUser(user, language)
    }

}