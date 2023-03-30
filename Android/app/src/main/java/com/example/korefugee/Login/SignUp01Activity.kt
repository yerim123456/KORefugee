package com.example.korefugee.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityLoginBinding
import com.example.korefugee.databinding.ActivitySignUp01Binding

class SignUp01Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp01Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivitySignUp01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // sign up 버튼 클릭 시
        binding.signupButton.setOnClickListener{

            if(binding.emailEditText.text.toString() != "" && binding.passwordEditText.text.toString() != "" && binding.pluspasswordEditText.text.toString() != ""){
                // 오류 메세지 전달 및 전부 지우기
                // email 오류
                // email이 이미 db에 있는지 확인
                // email 형식이 바른지 확인


                // password 오류
                // password 오류 메세지 받아와서 설정 및 보이도록 설정
                // password 맞는지 확인
                // password 보안 정도(8자 이상, 특수문자 포함 등) 체크

                // passwordplus 오류
                if( binding.pluspasswordEditText.text.toString() != binding.passwordEditText.text.toString()){
                    // 중복 비밀번호 확인이 잘못되었을 때
                    binding.errortextView.setText("비밀번호 겹친다.")
                    binding.errortextView.visibility = View.VISIBLE
                }
                else { // 받아온 오류 메세지
                    // 고민
                    // null 값으로 넘겨 주면서 검사를 해야할 지,
                }

                // 오류가 없다면
                // email, password String 전달
                binding.emailEditText.text.toString()
                binding.passwordEditText.text.toString()

                // sign up 화면 전환 + intent로 email과 password 정보 전달
                // 화면 전환
                val intent = Intent(this,SignUp02Activity::class.java)
                intent.putExtra("email", binding.emailEditText.text.toString())
                intent.putExtra("password", binding.passwordEditText.text.toString())
                startActivity(intent)

            }
        }



    }
}