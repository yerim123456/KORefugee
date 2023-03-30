package com.example.korefugee.Guide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.korefugee.Login.SignUp04Activity
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityGuideDocumenr01Binding
import com.example.korefugee.databinding.ActivityLoginBinding

class GuideDocumenr01Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityGuideDocumenr01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivityGuideDocumenr01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1Immgration.setOnClickListener {
            val intent = Intent(this, GuideDocument02Activity::class.java)
            startActivity(intent)
            // 제목 intent로 넘기기
        }

        // 나머지 세개의 버튼도 구성해놓기!
        binding.button2Stay.setOnClickListener {
            val intent = Intent(this, GuideDocument02_01Activity::class.java)
            startActivity(intent)
            // 제목 intent로 넘기기
        }

        binding.button3Objection.setOnClickListener {
            val intent = Intent(this, GuideDocument02_02Activity::class.java)
            startActivity(intent)
            // 제목 intent로 넘기기
        }
    }
}