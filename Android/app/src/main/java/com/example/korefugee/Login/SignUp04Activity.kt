package com.example.korefugee.Login

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.korefugee.*
import com.example.korefugee.databinding.ActivitySignUp04Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp04Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp04Binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp04Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.end.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }

}