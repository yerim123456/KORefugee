package com.example.korefugee.Community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.korefugee.R
import com.example.korefugee.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    private lateinit var translatebinding : FragmentCommunityBinding
    // Translated 로 수정!

    lateinit var way:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        translatebinding = FragmentCommunityBinding.inflate(inflater, container, false)

        translatebinding.wayRadioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId){
                R.id.camera -> {
                    way = "camera"
                }

                R.id.pdf -> {
                    way = "pdf"

                }
            }
        }
        translatebinding.startButton.setOnClickListener {
            val intent = Intent(activity, TranslateApp::class.java)
            intent.putExtra("way",way)
            startActivity(intent)
        }

        return translatebinding.root
    }

}