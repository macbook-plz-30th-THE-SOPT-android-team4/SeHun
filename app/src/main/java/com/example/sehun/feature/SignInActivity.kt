package com.example.sehun.feature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.data.remote.SoptClient
import com.example.sehun.data.remote.request.RequestSignIn
import com.example.sehun.data.remote.response.ResponseSignIn
import com.example.sehun.databinding.ActivitySignInBinding
import com.example.sehun.enqueueUtil
import com.example.sehun.feature.home.HomeActivity
import com.example.sehun.shortToast
import retrofit2.Call

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val myData: Intent? = result.data

                binding.etSigninId.setText(myData?.getStringExtra("id"))
                binding.etSigninPw.text.clear()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickEvent()
    }

    private fun clickEvent() {
        with(binding) {
            btnSigninLogin.setOnClickListener {
                val etId = etSigninId.text.toString()
                val etPw = etSigninPw.text.toString()

                if (etId.isEmpty() or etPw.isEmpty()) {
                    shortToast("로그인 실패")
                } else {
                    initNetwork()
                }
            }

            btnSigninSignup.setOnClickListener {
                resultLauncher.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun initNetwork() {
        val requestSignIn = RequestSignIn(
            id = binding.etSigninId.text.toString(),
            password = binding.etSigninPw.text.toString()
        )
        val call: Call<ResponseSignIn> = SoptClient.soptService.postSignIn(requestSignIn)

        call.enqueueUtil(onSuccess = {
            shortToast("${it.email}님 환영합니다!")
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
        })
    }
}
