package com.example.korefugee.Guide

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.korefugee.*
import com.example.korefugee.Voca.VocaMainActivity
import com.example.korefugee.Voca.VocalevelActivity
import com.example.korefugee.databinding.FragmentGuideBinding
import kotlinx.android.synthetic.main.customdialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuideFragment : Fragment() {

    private lateinit var guidebinding : FragmentGuideBinding

    lateinit var accesstoken:String
    lateinit var refreshtoken:String
    lateinit var name:String
    lateinit var language:String
    lateinit var date_voca:String

    // api 이용하기 위한 객체
    val api = APIS.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        guidebinding = FragmentGuideBinding.inflate(inflater, container, false)
        accesstoken = arguments?.getString("accesstoken").toString()
        refreshtoken = arguments?.getString("refreshtoken").toString()
        name = arguments?.getString("name").toString()
        language = arguments?.getString("language").toString()


        // get api로 사용자 이름 받아오기
        guidebinding.userName.setText(name)
        api.get_worddate("Bearer $accesstoken").enqueue(object : Callback<WordDate_R_Model> {
            // 응답하면
            override fun onResponse(call: Call<WordDate_R_Model>, response: Response<WordDate_R_Model>) {

                Log.d("응답",response.toString())
                Log.d("응답", response.body().toString())
                Log.d("응답",response.errorBody()?.string().toString())

                val my = response.body()

                if (my != null ) {
                    val date: List<WordDate_R_Model.Data> = my.data
                    if(my.success == "success"){
                        // 날짜 정보 받아와서 보여주기! - get api 이용!
                        date_voca = date[0].studyDate.toString()
                    }
                }
            }
            override fun onFailure(call: Call<WordDate_R_Model>, t: Throwable) {
                // 실패 시
                Log.d("응답",t.message.toString())
            }
        })
        // 가이드 이동
        guidebinding.preparelist.setOnClickListener{

            val intent = Intent(activity, GuideDocumenr01Activity::class.java)
            startActivity(intent)
        }

        // 화면 이동
        guidebinding.procedure.setOnClickListener{

            val intent = Intent(activity, GuideExplainActivity::class.java)
            startActivity(intent)
        }

       // 단어 이동
        guidebinding.today.setOnClickListener{

            val intent = Intent(activity, VocaMainActivity::class.java)
            intent.putExtra("date", date_voca)
            intent.putExtra("language", language)
            intent.putExtra("accesstoken", accesstoken)
            intent.putExtra("refreshtoken", refreshtoken)
            startActivity(intent)
        }
        guidebinding.medical.setOnClickListener{

            val intent = Intent(activity, VocalevelActivity::class.java)
            intent.putExtra("category", "Medical")
            intent.putExtra("language", language)
            intent.putExtra("accesstoken", accesstoken)
            intent.putExtra("refreshtoken", refreshtoken)
            startActivity(intent)
        }
        guidebinding.law.setOnClickListener{

            val intent = Intent(activity, VocalevelActivity::class.java)
            intent.putExtra("category", "Law")
            intent.putExtra("language", language)
            intent.putExtra("accesstoken", accesstoken)
            intent.putExtra("refreshtoken", refreshtoken)
            startActivity(intent)
        }
        guidebinding.life.setOnClickListener{

            val intent = Intent(activity, VocalevelActivity::class.java)
            intent.putExtra("category", "Daily")
            intent.putExtra("language", language)
            intent.putExtra("accesstoken", accesstoken)
            intent.putExtra("refreshtoken", refreshtoken)
            startActivity(intent)
        }


        // help 이동
        guidebinding.pnan.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdialog, null)
            mDialogView.title.setText("PNAN")

            mDialogView.location.setText("pnan@pnan.org")
            mDialogView.call.setText("02-871-5381")
            mDialogView.description.setText("It is an organization that supports accommodation for refugees, provides counseling, and legal support.")

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            mBuilder.show()
        }

        guidebinding.nancen.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdialog, null)
            mDialogView.title.setText("NANCEN")
            mDialogView.location.setText("refucenter@gmail.com")
            mDialogView.call.setText("02-712-0620")
            mDialogView.description.setText("It is an organization to support refugee accommodation and protect refugee human rights.")

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            mBuilder.show()
        }

        guidebinding.call.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdialog, null)
            mDialogView.title.setText("1345")

            mDialogView.location.setText("none")
            mDialogView.call.setText("1345")
            mDialogView.description.setText("Multi-language civil service information window that provides civil service counseling and administrative information necessary for foreigners in Korea to adapt to life in Korea")

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            mBuilder.show()
        }
        return guidebinding.root

    }

}