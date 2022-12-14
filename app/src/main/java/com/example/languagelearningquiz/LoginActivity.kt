package com.example.languagelearningquiz

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.languagelearningquiz.data.LanguageUser
import com.example.languagelearningquiz.databinding.ActivityLoginBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    fun registerUser() {
        if (isFormValid()) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(
                    this,
                    getString(R.string.registration_ok),
                    Toast.LENGTH_LONG
                ).show()
                // Create a new user with a first and last name

                val uid = it.user!!.uid
                val user = LanguageUser(
                    binding.etDisplayName.text.toString(),
                    getString(R.string.chinese),
                    0,
                    0,
                    uid,
                )
                FirebaseFirestore.getInstance().collection("languageusers").add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    fun loginUser() {
        if (isFormValid()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(
                    this,
                    getString(R.string.login_ok),
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this, chooseLanguageActivity::class.java))

            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }


    fun isFormValid(): Boolean {
        return when {
            binding.etEmail.text.isEmpty() -> {
                binding.etEmail.error = getString(R.string.error_email)
                false
            }
            binding.etPassword.text.isEmpty() -> {
                binding.etPassword.error = getString(R.string.error_password)
                false
            }
            binding.etDisplayName.text.isEmpty() -> {
                binding.etDisplayName.error = getString(R.string.error_displayname)
                false
            }
            else -> true
        }
    }
}