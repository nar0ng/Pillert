package com.example.pillert

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pillert.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode === android.app.Activity.RESULT_OK){
                // 이미지를 ImagView  보이기
                Glide
                    .with(applicationContext)
                    .load(it.data?.data)
                    .apply(RequestOptions().override(250, 200))
                    .centerCrop()
                    .into(binding.addImageView)
                // 이미지
                val cursor = contentResolver.query(it.data?.data as Uri, arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null)
                cursor?.moveToFirst().let{
                    
                }
            }
        }
        binding.btnGallery.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            requestLauncher.launch(intent)
        }
        binding.btnSave.setOnClickListener {
            if(binding.addEditView.text.isNotEmpty()){
                // firestore 저장
                saveStore()
            } else{
                Toast.makeText(this, "내용을 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    fun dateToString(date:Date):String{
        val format = SimpleDateFormat("yyyy-mm-dd")
        return format.format(date)
    }
    fun saveStore(){
        val data = mapOf(
            "email" to PillertApplication.email,
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )
        PillertApplication.db.collection("mypill")
            .add(data)
            .addOnSuccessListener {
                Log.d("mobileApp", "data firestore save ok")
            }
            .addOnFailureListener{
                Log.d("mobileApp", "data firestore save error")
            }
    }
}