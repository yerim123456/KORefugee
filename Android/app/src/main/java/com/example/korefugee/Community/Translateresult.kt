package com.example.korefugee.Community

import android.app.DownloadManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_translateresult.*
import java.net.MalformedURLException
import java.net.URL

class Translateresult : AppCompatActivity() {
    lateinit var filepath:String

    private var url: URL? = null
    private var fileName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translateresult)


        // intent로 category 받아오기
        if (intent.hasExtra("path")){
            filepath = intent.getStringExtra("path").toString()
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
        downloadButton.setOnClickListener {
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