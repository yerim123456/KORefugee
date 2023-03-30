package com.example.korefugee.My

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.korefugee.*
import com.example.korefugee.databinding.FragmentMyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyFragment : Fragment() {

    lateinit var accesstoken:String
    lateinit var refreshtoken:String

    // api 이용하기 위한 객체
    val api = APIS.create()

    private var _binding : FragmentMyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        accesstoken = arguments?.getString("accesstoken").toString()
        refreshtoken = arguments?.getString("refreshtoken").toString()

        // data 생성 및 정의
        // type -> birth
        // 데이터 구조에 맞춰서 회원가입 데이터 받아와서 넣기
        // 데이터 구조에 맞춰서 회원가입 데이터 받아와서 넣기


        return binding.root    }

}