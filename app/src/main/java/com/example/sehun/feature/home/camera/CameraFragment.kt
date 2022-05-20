package com.example.sehun.feature.home.camera

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sehun.databinding.FragmentCameraBinding
import com.example.sehun.shortToast

class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result: Boolean ->
            if (result) {
                requireContext().shortToast("권한요청이 승인되었습니다.")
                selectImage()
            } else
                requireContext().shortToast("권한요청이 거절되었습니다.")
        }
    val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            context?.let {
                Glide.with(it)
                    .load(uri)
                    .circleCrop()
                    .into(binding.ivCameraSelectedimage)
            }
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
            aboutPermission()
        }
    }

    private fun aboutPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            requireContext().shortToast("권한이 이미 있습니다.")
            selectImage()
        } else if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requireContext().shortToast("권한이 없습니다.")
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun selectImage() {

        getContent.launch("image/*")
    }
}
