package com.example.korefugee

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import com.example.korefugee.Login.LoginActivity
import com.example.korefugee.databinding.ActivityTranslateresultBinding
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import kotlinx.android.synthetic.main.activity_translate_app2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.MalformedURLException
import java.net.URL

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 3초 후 화면 이동
        val handler: Handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },1500)




    }



}