package com.example.korefugee.Community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.example.korefugee.*
import com.example.korefugee.databinding.ActivityTranslateresultBinding
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class Translateloading : AppCompatActivity() {

    // api 이용하기 위한 객체
    val api = APIS.create()

    lateinit var language_short:String
    lateinit var path:String


    private var url: URL? = null
    private var fileName: String? = null
    private var filepath: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translateloading)

        // 이메일 및 비밀번호 받아오기
        if (intent.hasExtra("language") && intent.hasExtra("file_path")) {
            language_short = intent.getStringExtra("language").toString()
            path =  intent.getStringExtra("file_path").toString()

        }


        val loading: ImageView = findViewById<View>(com.example.korefugee.R.id.gif_loading) as ImageView
        val gifImage = GlideDrawableImageViewTarget(loading)
        Glide.with(this).load<Any>(com.example.korefugee.R.drawable.loading).into<GlideDrawableImageViewTarget>(gifImage)

        // api 연결
        val intent = Intent(this, ActivityTranslateresultBinding::class.java)
        language_short = "en"
        path = "https://storage.googleapis.com/korefugee_trans/original/image.jpg"

        api.translate_get("http://34.64.122.97:8000/translate/${path}/${language_short}")
            .enqueue(object : Callback<JsonPrimitive> {
                // 응답하면
                override fun onResponse(call: Call<JsonPrimitive>
                                        , response: Response<JsonPrimitive>) {

                    Log.d("응답",response.toString())
                    Log.d("응답", response.body().toString())
                    Log.d("응답",response.errorBody()?.string().toString())
                    filepath = response.body().toString()

                    intent.putExtra("path", filepath)
                    startActivity(intent)

                }
                override fun onFailure(call: Call<JsonPrimitive>, t: Throwable) {
                    // 실패 시
                    Log.d("응답",t.message.toString())
                }
            })
    }
}