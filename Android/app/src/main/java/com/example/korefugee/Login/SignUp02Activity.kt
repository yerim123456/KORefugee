package com.example.korefugee.Login

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivitySignUp01Binding
import com.example.korefugee.databinding.ActivitySignUp02Binding
import kotlinx.android.synthetic.main.customdialog.view.*

// 뒤로가기 제한 두는 설정

class SignUp02Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp02Binding

    lateinit var email: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivitySignUp02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이메일 및 비밀번호 받아오기
        if (intent.hasExtra("email") && intent.hasExtra("password")) {
            email = intent.getStringExtra("email").toString()
            password =  intent.getStringExtra("password").toString()

        }
        binding.addexplain.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.explaindialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            mBuilder.show()
        }

        // 버튼 클릭에 따라 지위 저장하기 및 putExtra로 정보 전달
        binding.refugeeapplicantButton.setOnClickListener{
            // 지위 넘기고 화면 전환
            val intent = Intent(this,SignUp03Activity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("position", "Refugee Applicant")
            startActivity(intent)
        }
        binding.humanitarianstatusButton.setOnClickListener{
            // 지위 넘기고 화면 전환
            val intent = Intent(this,SignUp03Activity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("position", "Humanitarian Status")
            startActivity(intent)
        }
        binding.refugeeButton.setOnClickListener{
            // 지위 넘기고 화면 전환
            val intent = Intent(this,SignUp03Activity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("position", "Refugee")
            startActivity(intent)
        }
        binding.KoreanButton.setOnClickListener{
            // 지위 넘기고 화면 전환
            val intent = Intent(this,SignUp03Activity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("position", "Korean")
            startActivity(intent)
        }
    }
}