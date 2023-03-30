package com.example.korefugee.Guide

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.korefugee.databinding.ActivityGuideDocument03Binding
import java.io.File
import java.net.MalformedURLException
import java.net.URL


class GuideDocument03Activity : AppCompatActivity() {

    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityGuideDocument03Binding

    private val filepath = "https://storage.googleapis.com/korefugee_trans/original/c6b80ba1-6dc2-42ca-a7d1-cbdd43552033.png" // 파이어베이스에서 받아온 URL 넣기!

    // 파이어베이스 URL
    lateinit var title:String
    private var url: URL? = null
    private var fileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGuideDocument03Binding.inflate(layoutInflater)
        setContentView(binding.root)
        // 이메일 및 비밀번호, 지위 등 받아오기
        if (intent.hasExtra("title")) {
            title = intent.getStringExtra("title").toString()
        }
        binding.papertitle.setText(title)

        binding.back.setOnClickListener{
            val intent = Intent(this, GuideDocumenr01Activity::class.java)
            startActivity(intent)
        }

        initViews()
        setListeners()
    }

    private fun initViews() {
        try {
            url = URL(filepath)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        fileName = url?.getPath()
        fileName = fileName!!.substring(fileName!!.lastIndexOf('/') + 1)
    }

    private fun setListeners() {
        binding.button2.setOnClickListener {
            val request =
                DownloadManager.Request(Uri.parse(url.toString() + ""))
            request.setTitle(fileName)
            request.setMimeType("applcation/pdf")
            request.allowScanningByMediaScanner()
            request.setAllowedOverMetered(true)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )
            val dm =
                getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
        }

    }


}