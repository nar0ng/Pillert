package com.example.pillert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pillert.MainActivity
import com.example.pillert.databinding.ActivityEmailLoginBinding
import com.example.pillert.PillertApplication

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val email: String = binding.editEmail.text.toString()
            val password: String = binding.editPassword.text.toString()

            PillertApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val currentUser = PillertApplication.auth.currentUser
                        if (currentUser != null && currentUser.isEmailVerified) {
                            Toast.makeText(baseContext, "로그인 성공!", Toast.LENGTH_LONG).show()
                            // 메인 페이지로 이동하는 코드 추가
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(baseContext, "이메일 인증 실패 ...", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(baseContext, "로그인 실패 ...", Toast.LENGTH_LONG).show()
                    }

                    // 다음 입력이 가능하도록 입력창 지우기
                    binding.editEmail.text.clear()
                    binding.editPassword.text.clear()
                }
        }
    }
}
