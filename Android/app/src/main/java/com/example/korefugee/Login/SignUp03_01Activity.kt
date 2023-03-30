package com.example.korefugee.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.korefugee.*
import com.example.korefugee.databinding.ActivitySignUp0301Binding
import com.example.korefugee.databinding.ActivitySignUp03Binding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp03_01Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp0301Binding

    lateinit var email: String
    lateinit var password: String
    lateinit var position: String
    lateinit var name: String
    lateinit var birth: String
    lateinit var gender: String
    lateinit var language_short: String

    // api 이용하기 위한 객체
    val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivitySignUp0301Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이메일 및 비밀번호, 지위 등 받아오기
        if (intent.hasExtra("email") && intent.hasExtra("password")
            && intent.hasExtra("position") && intent.hasExtra("name")
            && intent.hasExtra("birth") && intent.hasExtra("gender")) {

            email = intent.getStringExtra("email").toString()
            password =  intent.getStringExtra("password").toString()
            position = intent.getStringExtra("position").toString()
            name = intent.getStringExtra("name").toString()
            birth = intent.getStringExtra("birth").toString()
            gender = intent.getStringExtra("gender").toString()

        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }



        // 나라 팝업 메뉴 내려오기
        binding.countryTextView2.setOnClickListener(View.OnClickListener { v ->
            val popupMenu = PopupMenu(this, v)
            val inflater = popupMenu.menuInflater
            val menu = popupMenu.menu
            inflater.inflate(R.menu.countrylist, menu)
            popupMenu.setOnMenuItemClickListener { item ->

                var x =item.title.toString()  // 메뉴의 타이틀을 불러와서

                binding.countryTextView2.setText(x)    // 카테고리 텍스트뷰에 출력해줌

                false
            }
            popupMenu.show()
        })

        val jsonString = assets.open("lang_list.json").reader().readText()
        val jsonArray = JSONArray(jsonString)

        binding.languageTextView2.setOnClickListener(View.OnClickListener { v ->
            val popupMenu = PopupMenu(this, v)
            val inflater = popupMenu.menuInflater
            val menu = popupMenu.menu
            inflater.inflate(R.menu.languagelist, menu)
            popupMenu.setOnMenuItemClickListener { item ->

                var x =item.title.toString()  // 메뉴의 타이틀을 불러와서

                binding.languageTextView2.setText(x)    // 카테고리 텍스트뷰에 출력해줌
                // 그 나라 언어와 연결하기
                for (index in 0 until jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(index)

                    val name = jsonObject.getString("name")
                    val language = jsonObject.getString("language")

                    if(name == x){
                        language_short = language
                        Log.d("언어", language_short)
                    }
                }
                false
            }
            popupMenu.show()
        })



        // 오류 메세지 출력
        // 결과 저장 및 넘기기
        binding.completeButton.setOnClickListener{

            val data = SignUpModel(email, password, name, birth, gender,
                position, binding.countryTextView2.text.toString(), language_short)

            val intent = Intent(this, SignUp04Activity::class.java)

            // 데이터 구조에 맞춰서 회원가입 데이터 받아와서 넣기
            api.post_register(data).enqueue(object : Callback<SignUp_R_Model> {
                // 응답하면

                override fun onResponse(
                    call: Call<SignUp_R_Model>,
                    response: Response<SignUp_R_Model>
                ) {
                    Log.d("응답",response.toString())
                    Log.d("응답", response.body().toString())
                    Log.d("응답",response.errorBody()?.string().toString())
                    Log.d("응답",email + password + name+ birth+ gender+
                            position + binding.countryTextView2.text.toString() + language_short)


                    val responseerror = response.body()
                    if (responseerror != null) {
                        Log.d( "s",responseerror.error.toString())
                    }
                    startActivity(intent)
                }

                override fun onFailure(call: Call<SignUp_R_Model>, t: Throwable) {
                    // 실패 시
                    Log.d("응답",t.message.toString())
                    Log.d("응답","fail")
                }

            })
        }
    }
}