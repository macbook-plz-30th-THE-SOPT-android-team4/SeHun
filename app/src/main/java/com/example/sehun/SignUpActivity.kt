package com.example.sehun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sehun.databinding.ActivitySignUpBinding

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

                fun passingIntent() {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", etId)
                    intent.putExtra("pw", etPw)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

                if (etId.isEmpty() || etPw.isEmpty() || etName.isEmpty()) {
                    shortToast("입력되지 않은 정보가 있습니다")
                } else {
                    shortToast("회원가입이 완료되었습니다")
                    passingIntent()
                }
            }
        }
    }
}