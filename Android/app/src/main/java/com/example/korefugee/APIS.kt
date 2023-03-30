package com.example.korefugee

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface APIS {

    // 번역 api
    @GET("{fullUrl}")
    fun translate_get(
        @Path(
            value = "fullUrl",
            encoded = true
        ) original_file_path: String

    ): Call<JsonPrimitive>

    // 로그인 api
    @POST("/api/login")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_users(
        @Body jsonparams: LoginModel
    ): Call<Login_R_Model>

    // 회원가입 api
    @POST("/api/register")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_register(
        @Body jsonparams: SignUpModel
    ): Call<SignUp_R_Model>

    // 단어 가져오는(파트 별)
    @GET("/api/word")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun get_word(
        @Header("Authorization") Authorization: String?,
        @Query("date") date: String,
        @Query("level") level: String,
        @Query("type") type: String?
    ): Call<WordList_R_Model>


    @GET("/api/my")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun get_my(
        @Header("Authorization") Authorization: String?
        ): Call<MY_R_Model>

    @GET("api/mDate")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun get_worddate(
        @Header("Authorization") Authorization: String?
    ): Call<WordDate_R_Model>


    // http://34.64.248.167:5000/api/login
    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://34.64.122.97:5000"+ "/" // 주소
        fun create(): APIS {
            val gson : Gson =   GsonBuilder().setLenient().create();
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)


        }


    }

}
