## :notebook_with_decorative_cover:구현 결과
<img src="https://user-images.githubusercontent.com/81347125/167109903-d18f55f1-ec5f-4558-a3ad-17d20706f196.gif" width="30%"> 
<br>



## :notebook_with_decorative_cover:2주차 필수과제 : Follower, Repository Fragment에 RecyclerView 구현
<img src="https://user-images.githubusercontent.com/81347125/167101051-5d32e5c2-41f0-445a-981d-0727276ceae6.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101048-0037fd6b-d06f-4cc6-8ec8-44d387a2eff2.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101046-1126c3d9-8361-438e-8680-efe5c4dafbc3.png" width="33%">
<br>

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

### :pushpin:사진 비율 1:1 및 스크롤뷰 구현(constraintDimensionRatio, ScrollView)
<pre>
<code>

 app:layout_constraintDimensionRatio="1:1"
 
</code>
</pre>

## :notebook_with_decorative_cover:2주차 성장과제 : Intent 전달, RecyclerView item Event 및 ItemDecoration 구현 
<img src="https://user-images.githubusercontent.com/81347125/167101041-465064d0-2caa-4178-8149-93db80038127.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101036-348e2e96-9e72-4840-92d0-2a7e353d02b3.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101026-f49cff09-7b41-42d4-b81d-e8d999aa6edc.png" width="33%">
<br>

## :notebook_with_decorative_cover:2주차 도전과제 : 보일러 플레이트 코드 및 notifyDataSetChanged 개선방안
<img src="https://user-images.githubusercontent.com/81347125/167100998-7f1b1e02-c798-43c4-9a42-736913aa13b6.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167100964-20953aef-21e9-43a0-961d-5bff0e6e4f07.png" width = "33%"> 
<br>ter("imgResId")
    fun setImageResId(imageview: ImageView, resId: Int) {
        imageview.setImageResource(resId)
    }
}
</code>
</pre>

<pre>
<code>
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        dataBinding()
    }

    private fun dataBinding() {
        binding.home = HomeData(
            "김세훈",
            "25",
            "ENFJ",
            "우하하\n".repeat(100),
            R.drawable.ic_launcher_foreground
        )
    }
}
</code>
</pre>

<pre>
<code>
data class HomeData(
    val name: String,
    val age: String,
    val mbti: String,
    val introduce: String,
    val resid: Int
)

</code>
</pre>

<pre>
<code>
 data>

        variable
            name="home"
            type="com.example.sehun.feature.home.HomeData" />
    /data>
</code>
</pre>

<pre>
<code>
  app:imgResId="@{home.resid}"
</code>
</pre>
<br>
