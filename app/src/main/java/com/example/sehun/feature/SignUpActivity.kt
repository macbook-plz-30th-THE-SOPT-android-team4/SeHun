package com.example.sehun.feature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.data.remote.SoptClient
import com.example.sehun.data.remote.request.RequestSignUp
import com.example.sehun.databinding.ActivitySignUpBinding
import com.example.sehun.enqueueUtil
import com.example.sehun.shortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickEvent()
    }

    private fun clickEvent() {
        with(binding) {
            btnSignupDone.setOnClickListener {

                val etName = etSignupName.text.toString()
                val etId = etSignupId.text.toString()
                val etPw = etSignupPw.text.toString()

                if (etId.isEmpty() or etPw.isEmpty() or etName.isEmpty()) {
                    shortToast("입력되지 않은 정보가 있습니다!")
                } else {
                    initNetwork()
                }
            }
        }
    }

    private fun initNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etSignupName.text.toString(),
            id = binding.etSignupId.text.toString(),
            password = binding.etSignupPw.text.toString()
        )
        val call = SoptClient.soptService.postSignUp(requestSignUp)

        call.enqueueUtil(onSuccess = {
            shortToast("회원가입이 완료되었습니다!")
            passingIntent(requestSignUp.id)
        })
    }

    private fun passingIntent(etId: String) {
        val intent = Intent(this, SignInActivity::class.java)

        intent.putExtra("id", etId)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
