package com.example.pillert

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PillertApplication : MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null

        fun checkAuth(): Boolean {
            val currentUser = auth.currentUser
            return if (currentUser != null && currentUser.isEmailVerified) {
                email = currentUser.email
                true
            } else {
                false
            }
        }


    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
    }
}
