package com.example.korefugee.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.marginTop
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivitySignUp02Binding
import com.example.korefugee.databinding.ActivitySignUp03Binding
// Korean 선택 시 margin 변경
// 라디오 버튼으로 성별 선택하는 방법 알아보기

class SignUp03Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp03Binding

    lateinit var email: String
    lateinit var password: String
    lateinit var position: String
    lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivitySignUp03Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이메일 및 비밀번호, 지위 받아오기
        if (intent.hasExtra("email") && intent.hasExtra("password")) {
            email = intent.getStringExtra("email").toString()
            password =  intent.getStringExtra("password").toString()
            position = intent.getStringExtra("position").toString()
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }



        // 라디오 그룹인 성별 선택 시 저장되는 성별 적용
        binding.genderRadioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId){
                R.id.female -> gender = "female"

                R.id.male -> gender = "male"

                R.id.selfidentity -> gender = "self_identity"
            }
        }

        // 이름, Gender, Birthday
        // field 넣기!
        binding.NextButton.setOnClickListener{
            // 값 저장 및 intent로 넘기기
            // 오류 메세지 적용
            if(binding.birthdayEditText.text.toString() != null && gender != null && binding.nameEditText.text.toString() != null){
                // 데이터 베이스에 값 보내기
                gender
                binding.birthdayEditText.text.toString()
                binding.nameEditText.text.toString()
                if(position == "Korean"){
                }else{
                    null
                }

                // 화면 전환 및 값 넘기기
                val intent = Intent(this,SignUp03_01Activity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("position", position)
                intent.putExtra("name", binding.nameEditText.text.toString())
                intent.putExtra("birth", binding.birthdayEditText.text.toString())
                intent.putExtra("gender", gender)
                startActivity(intent)

            }
                // 수정된 주석

            else{

            }
        }
    }
}