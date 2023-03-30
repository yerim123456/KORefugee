package com.example.korefugee.Community

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityTranslateApp2Binding
import org.json.JSONArray
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class TranslateApp : AppCompatActivity() {
    lateinit var binding: ActivityTranslateApp2Binding

    lateinit var way:String

    lateinit var filePath: String

    lateinit var language_short: String
    private val REQUEST_PERMISSIONS = 1

    private val timeStamp: String =
        SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    private val outputFilePath = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS + "/KORefugees"
    ).toString() + "/Translate_${timeStamp}_.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTranslateApp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("way")) {
            way = intent.getStringExtra("way").toString()
        }

        val jsonString = assets.open("lang_list.json").reader().readText()
        val jsonArray = JSONArray(jsonString)

        binding.language.setOnClickListener(View.OnClickListener { v ->
                val popupMenu = PopupMenu(this, v)
                val inflater = popupMenu.menuInflater
                val menu = popupMenu.menu
                inflater.inflate(R.menu.languagelist, menu)
                popupMenu.setOnMenuItemClickListener { item ->

                    var x =item.title.toString()  // 메뉴의 타이틀을 불러와서

                    binding.language.setText(x)    // 카테고리 텍스트뷰에 출력해줌
                    // 그 나라 언어와 연결하기
                    for (index in 0 until jsonArray.length()){

                        val jsonObject = jsonArray.getJSONObject(index)
                        val name = jsonObject.getString("name")
                        val language = jsonObject.getString("language")

                        if(name == x){
                            language_short = language
                        }
                    }
                    false
                }
                popupMenu.show()
            })

        // camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            Log.d("이미지",filePath)
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                // 보여주고 싶은 곳
            }
        }

        // document request launcher.................
        val requestDocumentFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // call back 처리
            try {
                /*
                // 이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!) // 로딩해야 하는 이미지 파일
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option) // 넘어오는 이미지를 갖고 bitmap 파일 만들기, option의 사이즈 적용해서
                inputStream!!.close()
                inputStream = null
                bitmap?.let {

                } ?: let {
                    Log.d("tagg", "bitmap null")
                }

                 */
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        val requestPDF =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                it.data?.data?.let { uri->
                    contentResolver.takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    Log.d("A",uri.toString())
                    getRealPathFromURI(uri)?.let { it1 -> Log.d("A", it1) }
                }
            }

        if(way == "camera"){
            // 카메라 앱 실행 및 api로 넘기기
           val storageDir: File? =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES) // 외장 메모리 공간에 저장하겠다
            val file = File.createTempFile(
                "JPEG_${timeStamp}_", // 이름
                ".jpg", // 형식
                storageDir // 저장될 공간
            )
            filePath = file.absolutePath // 절대 경로로
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.korefugee.fileprovider", file
            )

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 카메라 앱 구동
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI) // 우리 파일에 write 해줌 - 파일 정보 넘기기
            requestCameraFileLauncher.launch(intent)
        }
        else if(way == "pdf") {
            // 갤러리 접근 권한 물어보기 및 얻어오기


            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "application/pdf"
            requestPDF.launch(intent)

            /*
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type
            requestGalleryLauncher.launch(intent) // 이거 실행되면서 call back 부분 실행됨

             */
        }

        binding.startButton.setOnClickListener {
            val intent = Intent(this, Translateloading::class.java)
            intent.putExtra("language",language_short)
            // 파일 건네줄 수 있는 방법 모색...
            intent.putExtra("file_path",filePath)
            startActivity(intent)
        }


    }

    private fun checkPermission() {
        var permission = mutableMapOf<String, String>()
        permission["storageRead"] = Manifest.permission.READ_EXTERNAL_STORAGE
        permission["storageWrite"] =  Manifest.permission.WRITE_EXTERNAL_STORAGE

        // 현재 권한 상태 검사
        var denied = permission.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        // 마시멜로 버전 이후
        if(denied > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_PERMISSIONS)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            // inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            // 로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
    private fun getRealPathFromURI(contentUri: Uri): String? {
        if (contentUri.path!!.startsWith("/document")) {
            return contentUri.path
        }
        val id = DocumentsContract.getDocumentId(contentUri).split(":".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()[1]
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id
        val cursor = contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            columns,
            selection,
            null,
            null
        )
        try {
            val columnIndex = cursor!!.getColumnIndex(columns[0])
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor!!.close()
        }
        return null
    }
}