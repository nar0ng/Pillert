package com.example.pillert

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000 // 2초 동안 스플래시 화면 표시

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, LanguageActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}
