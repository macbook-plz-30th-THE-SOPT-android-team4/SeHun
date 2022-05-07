package com.example.sehun.feature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.data.remote.SoptClient
import com.example.sehun.data.remote.request.RequestSignIn
import com.example.sehun.data.remote.response.ResponseSignIn
import com.example.sehun.databinding.ActivitySignInBinding
import com.example.sehun.feature.home.HomeActivity
import com.example.sehun.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val myData: Intent? = result.data

                binding.etSigninId.setText(myData?.getStringExtra("id"))
                binding.etSigninPw.setText(myData?.getStringExtra("pw"))
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

                if (etId.isEmpty() || etPw.isEmpty()) {
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

        call.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    shortToast("${data?.email}님 ㅎㅇ")
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                } else shortToast("ㄲㅂ")
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("ㅄ", "ㅂㅂ")
            }
        })
    }
}
