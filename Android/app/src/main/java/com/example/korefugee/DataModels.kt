package com.example.korefugee

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

// request model
data class LoginModel(

    var email : String? =null ,

    var password : String? =null
)


data class SignUpModel(
    var email : String? =null ,
    var password : String? =null,
    var name : String? =null,
    var birth : String? =null,
    var gender : String? =null,
    var status : String? =null,
    var nation : String? =null,
    var language : String? =null
)

data class WordModel(
    var level : String? =null ,

    var type : String? =null
)

// Respond Model
data class Login_R_Model(
    val code: Int,
    val error: String,
    val success: String,
    val token: Token
){
    data class Token(
        val accesstoken: String,
        val refreshtoken: String
    )
}

data class SignUp_R_Model(
    var success:String? = null,
    var error:String? = null
)

data class MY_R_Model(
    val data: Data,
    val error: String,
    val message: String,
    val success: String
) {
    data class Data(
        val birth: String,
        val email: String,
        val gender: String,
        val language: String,
        val name: String,
        val nation: String,
        val password: String,
        val status: String,
        val userId: Int
    )
}

data class WordDate_R_Model(
    val data: List<Data>,
    val error: String,
    val success: String
) {
    data class Data(
        val studyDate: Int
    )
}

data class Translate_R_Model(
    val detail: List<Detail>
) {
    data class Detail(
        val loc: List<Any>,
        val msg: String,
        val type: String
    )
}
data class WordList_R_Model(
    val `data`: List<Data>,
    val error: String,
    val success: String
) {
    data class Data(
        val wordId: Int,
        val words: String
    )
}