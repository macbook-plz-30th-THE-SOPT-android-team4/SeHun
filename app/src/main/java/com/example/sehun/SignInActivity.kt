package com.example.sehun

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
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
                    shortToast("${etId}님 환영합니다")
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                }
            }

            btnSigninSignup.setOnClickListener {
                resultLauncher.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
        }
    }

}