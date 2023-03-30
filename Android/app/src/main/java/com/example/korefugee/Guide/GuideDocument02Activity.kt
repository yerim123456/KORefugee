package com.example.korefugee.Guide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityGuideDocumenr01Binding
import com.example.korefugee.databinding.ActivityGuideDocument02Binding

class GuideDocument02Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityGuideDocument02Binding

    // 제목 intent로 받기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivityGuideDocument02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            val intent = Intent(this, GuideDocumenr01Activity::class.java)
            startActivity(intent)
        }

        binding.goButton1.setOnClickListener {
            val intent = Intent(this, GuideDocument03Activity::class.java)
            intent.putExtra("title", binding.title.text.toString())
            startActivity(intent)
            // 제목과 서류 제목 intent로 넘기기기
        }
   }
}