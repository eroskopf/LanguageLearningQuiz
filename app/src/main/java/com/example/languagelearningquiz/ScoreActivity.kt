package com.example.languagelearningquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagelearningquiz.databinding.ActivityScoreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ScoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityScoreBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

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

        database = Firebase.database.reference

        database.child("users").child(id).child("score").setValue(SCORE)

        //check if the user is already in the firebase scoreboard
        if (id is in )

        /*
        this is for the sign in screen not the sign out screen
        customToken?.let {
            auth.signInWithCustomToken(it)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

         */


    }
}