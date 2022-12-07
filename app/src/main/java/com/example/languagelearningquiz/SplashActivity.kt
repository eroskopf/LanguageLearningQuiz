package com.example.languagelearningquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.languagelearningquiz.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashAnim = AnimationUtils.loadAnimation(
            this,
            R.anim.logo_anim
        )

        splashAnim.setAnimationListener(

            object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            }
        )
        binding.logo.startAnimation(splashAnim)











    }
}