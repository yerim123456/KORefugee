package com.example.korefugee.Voca

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.korefugee.APIS
import com.example.korefugee.R
import com.example.korefugee.WordList_R_Model
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateLanguage.*
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.activity_voca_main.*
import kotlinx.android.synthetic.main.voca_page.view.*
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class ViewPagerAdapter2(accesstoken:String, date:Int, level:String,
                        category:String, language: String) : PagerAdapter(){
    // api 이용하기 위한 객체
    val api = APIS.create()

    private var mContext: Context?=null

    var t_accesstoken:String = accesstoken
    var t_date:Int = date
    var t_level:String = level
    var t_category:String = category
    var t_language:String = language

    var id:String = language


    fun ViewPagerAdapter(context: Context){
        mContext=context;

        Log.d("응답","bbbbbbbbbbbb")
    }

    //position에 해당하는 페이지 생성
    @OptIn(ExperimentalTime::class)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // page.xml 과 연결
        Log.d("응답",t_language)

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(KOREAN)
            .setTargetLanguage(ENGLISH)
            .build()


        val koreanTranslator = Translation.getClient(options)

        // 와이파이를 이용하기 위한 상태 체크
        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        var view= LayoutInflater.from(container.context).inflate(R.layout.voca_page,container,false)


        api.get_word("Bearer $t_accesstoken", "${t_date}" ,"$t_level", "$t_category")
            .enqueue(object : Callback<WordList_R_Model> {
                // 응답하면
                override fun onResponse(
                    call: Call<WordList_R_Model>,
                    response: Response<WordList_R_Model>
                ) {
                    Log.d("응답", response.toString())
                    Log.d("응답", response.body().toString())
                    Log.d("응답", response.errorBody()?.string().toString())
                    val responsedata = response.body()

                    if (responsedata != null) {
                        val vocalist: List<WordList_R_Model.Data> = responsedata.data
                        if (responsedata.success == "success") {
                            Log.e("z", vocalist.size.toString())
                            view.Koreantext.text = vocalist[position].words
                            id = vocalist[position].wordId.toString()

                            val mtValue = measureTimedValue {
                                koreanTranslator.downloadModelIfNeeded(conditions)
                                    .addOnSuccessListener {
                                        koreanTranslator.translate(vocalist[position].words)
                                            .addOnSuccessListener { translatedText ->
                                                // Translation successful.
                                                view.Nativetext.text = translatedText.toString()
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("ssssssss1", exception.toString())
                                            }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e("ssssssss2", exception.toString())
                                    }
                            }


                        }


                    }
                }

                override fun onFailure(call: Call<WordList_R_Model>, t: Throwable) {
                    // 실패 시
                    Log.d("응답",t.message.toString())
                    Log.d("응답","fail")
                }
            })

        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        // 날짜 별 개수로 화면 구성

        return 3
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

}