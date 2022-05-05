package com.example.sehun.feature.home.camera

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.sehun.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.ivCameraSelectedimage.setImageURI(uri)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)

        clickEvent()
        return binding.root
    }

    private fun clickEvent() {

        binding.btnCameraUpload.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {

        getContent.launch("image/*")
    }
}
