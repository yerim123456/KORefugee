package com.example.korefugee

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.example.korefugee.Community.CommunityFragment
import com.example.korefugee.Guide.GuideFragment
import com.example.korefugee.Map.MapActivity
import com.example.korefugee.My.MyFragment
import com.example.korefugee.Voca.VocaFragment
import com.example.korefugee.databinding.ActivityMainBinding
import com.example.korefugee.databinding.FragmentGuideBinding
import com.google.android.material.navigation.NavigationBarView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener  {

    lateinit var accesstoken:String
    lateinit var refreshtoken:String
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityMainBinding

    lateinit var language: String
    lateinit var name: String

    // api 이용하기 위한 객체
    val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이메일 및 비밀번호 받아오기
        if (intent.hasExtra("accesstoken") && intent.hasExtra("refreshtoken")&&
             intent.hasExtra("refreshtoken")&& intent.hasExtra("refreshtoken")) {
            accesstoken = intent.getStringExtra("accesstoken").toString()
            refreshtoken =  intent.getStringExtra("refreshtoken").toString()
            language =  intent.getStringExtra("language").toString()
            name =  intent.getStringExtra("name").toString()

        }


        // 바텀 네비게이션과 바인딩하여
        binding.bottomNavigation.setOnItemSelectedListener(this)

        // 첫 화면 설정 위한 변수 설정
        val fragmentmanger: FragmentManager = supportFragmentManager
        val guidefragment = GuideFragment()
        val bundle = Bundle()
        bundle.putString("accesstoken", accesstoken)
        bundle.putString("refreshtoken", refreshtoken)
        bundle.putString("name", name)
        bundle.putString("language", language)
        guidefragment.arguments = bundle
        val transaction = fragmentmanger.beginTransaction()

        // 첫 화면 설정
        transaction.add(R.id.main_content, guidefragment)
        transaction.commit()

    }

    override fun onNavigationItemSelected(p0 : MenuItem): Boolean { // 바텀 네비게이션 클릭 시 이동

        val bundle = Bundle()
        bundle.putString("accesstoken", accesstoken)
        bundle.putString("refreshtoken", refreshtoken)
        when(p0.itemId){
            R.id.action_guide->{
                var guideFragment = GuideFragment()
                bundle.putString("name", name)
                bundle.putString("language", language)
                guideFragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.main_content,guideFragment).commit()
                return true
            }
            R.id.action_map->{
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra("accesstoken", accesstoken)
                intent.putExtra("refreshtoken", refreshtoken)
                startActivity(intent)
            }

            R.id.action_voca->{
                var vocaFragment = VocaFragment()
                bundle.putString("language", language)
                vocaFragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.main_content,vocaFragment).commit()
                return true
            }
            R.id.action_community->{
                var communityFragment = CommunityFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,communityFragment).commit()
                return true
            }
            R.id.action_my -> {
                var myFragment = MyFragment()
                // 필요한 데이터 추가하기!
                // 최종적으로 쓰고 위에서 받아온 데이터 지우기
                myFragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.main_content,myFragment).commit()
                return true
            }
        }
        return false
    }
}