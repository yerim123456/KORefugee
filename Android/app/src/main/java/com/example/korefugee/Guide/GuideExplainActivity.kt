package com.example.korefugee.Guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_guide_explain.*

class GuideExplainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_explain)


        //어댑터 연결하기
        val adapter=ViewPagerAdapter()
        pager.adapter=adapter


    }
}