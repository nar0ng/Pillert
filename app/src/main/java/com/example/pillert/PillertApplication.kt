package com.example.pillert

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PillertApplication : MultiDexApplication() {

    companion object {
        lateinit var db : FirebaseFirestore
        lateinit var storage: FirebaseStorage
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

        var networkService : NetworkService
        val retrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java) // 초기화
        }


    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}
