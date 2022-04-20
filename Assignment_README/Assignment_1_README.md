## :notebook_with_decorative_cover:1주차 필수과제 : 로그인, 회원가입, 자기소개 페이지 만들기
<img src="https://user-images.githubusercontent.com/81347125/164273755-3fdae97f-48f9-493c-a734-a2c2a08f1aad.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164275877-09af936b-fb05-45b4-989d-484f41271725.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164275892-ea6f48d3-448b-49ba-9e08-f8e01043bec9.jpg" width="33%">
<br>

## :notebook_with_decorative_cover:1주차 성장과제 : 화면이동 + @, 사진 1:1 비율 및 스크롤 구현
<img src="https://user-images.githubusercontent.com/81347125/164283543-546238d9-593a-44f3-81b8-27174ba3d30c.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164283553-a906587e-8c60-4fb1-bd25-bd767869635f.jpg" width = "33%"> 
<br>

## :notebook_with_decorative_cover:1주차 도전과제 : DataBinding 및 MVVM 구현
<img src="https://user-images.githubusercontent.com/81347125/164284287-2224e365-c1ee-41a8-91c1-46770970ea4a.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164284514-c3f22daf-7efe-46d2-8e56-44c50a24e244.jpg" width = "33%"> 
<br>


## :notebook_with_decorative_cover:구현 결과
<img src="https://user-images.githubusercontent.com/81347125/164276484-398e7dd2-a414-46dc-b4f5-8d4139234367.gif" width="30%"> 
<br>

## :notebook_with_decorative_cover:과제 리뷰
### :pushpin:로그인 페이지 만들기(SignInActivity)
<pre>
<code>
// 아이디, 비밀번호 모두 입력이 되어있을 때만 로그인 버튼을 눌렀을때 HomeActivity로 이동

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
</code>
</pre>

<pre>
<code>
// 토스트 메시지 출력
    
 fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }
  shortToast("아이디/비밀번호를 확인해주세요")
          
</code>
</pre>

### :pushpin:회원가입 페이지 만들기(SignUpActivity)
<pre>
<code>
// 이름, 아이디, 비밀번호 모두 입력이 되어있을 때만 회원가입 버튼을 눌렀을때 다시 SignInActivity로 이동(이때는 intent가 아닌 finish 활용)
// 셋 중 하나라도 비어있다면 "입력되지 않은 정보가 있습니다" 라는 토스트 메시지 출력

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
</code>
</pre>

### :pushpin:화면이동 및 인텐트 전달(registerForActivityResult, putExtra)
<pre>
<code>
// 회원가입 성공 시 이전 로그인 화면으로 돌아옴
// 이때 회원가입에서 입력했던 아이디 및 비밀번호가 그대로 전달돼야함

   fun passingIntent() {
                 val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                 intent.putExtra("id", etId)
                 intent.putExtra("pw", etPw)
                 setResult(Activity.RESULT_OK, intent)
                 finish()
             }

           private lateinit var binding: ActivitySignInBinding
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val myData: Intent? = result.data

                binding.etSigninId.setText(myData?.getStringExtra("id"))
                binding.etSigninPw.setText(myData?.getStringExtra("pw"))
            }
        }
</code>
</pre>
<br>