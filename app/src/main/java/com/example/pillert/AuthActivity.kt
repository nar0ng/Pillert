package com.example.pillert


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // 구글 로그인
        val btn_Googlelogin = findViewById<Button>(R.id.btn_Googlelogin)
        btn_Googlelogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 이메일 로그인
        val btn_Emaillogin  = findViewById<Button>(R.id.btn_Emaillogin)
        btn_Emaillogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 방문자 로그인
        val btn_Guestlogin = findViewById<Button>(R.id.btn_Guestlogin)
        btn_Guestlogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 회원가입
        val btn_Signin = findViewById<Button>(R.id.btn_Signin)
        btn_Signin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}