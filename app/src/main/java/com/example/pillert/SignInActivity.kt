package com.example.pillert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.pillert.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 누르면
        binding.signBtn.setOnClickListener{
            val email:String = binding.signinEmail.text.toString()
            val password:String = binding.signinPw.text.toString()

            PillertApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful){
                        PillertApplication.auth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                            sendTask ->
                            if(sendTask.isSuccessful){
                                Toast.makeText(baseContext, "회원가입 성공! 이메일 확인", Toast.LENGTH_LONG).show()
                            }
                            else  {
                                Toast.makeText(baseContext, "메일 전송 실패 ...", Toast.LENGTH_LONG).show()

                            }
                        }
                    }
                    else {
                        Toast.makeText(baseContext, "회원가입 실패 ...", Toast.LENGTH_LONG).show()
                    }
                    binding.signinEmail.text.clear()
                    binding.signinPw.text.clear()
                    }
                }
        }
    }

