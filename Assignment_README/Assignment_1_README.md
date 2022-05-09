# &#128154; Android-SeHun 
## &#128204; 1 week

| Task           |1 week|2 week|3week|4 week|5 week|6 week|7 week|
|----------------|---------------|---------------|----------------|-----------|-----------|-----------|-----------|
| 필수 과제 | <ul><li> [x] 1-1</li><li> [x] 1-2</li><li> [x] 1-3 |　- |　- |　- |　- |　- | 　- |
| 성장 과제 | <ul><li> [x] 2-1</li><li> [x] 2-2 |　- |　- | 　- |　- |　- |　- |
| 도전 과제 | <ul><li> [x] 3-1</li><li> [ ] 3-2 |　- |　- | 　- |　- |　- |　- |

<br/>

## &#128204; 구현 결과
<img src="https://user-images.githubusercontent.com/81347125/164276484-398e7dd2-a414-46dc-b4f5-8d4139234367.gif" width="25%"> 
<br/>
 
## &#128204; 과제 리뷰
### &#10004; 필수과제 : 로그인, 회원가입, 자기소개 페이지 만들기
<img src="https://user-images.githubusercontent.com/81347125/164273755-3fdae97f-48f9-493c-a734-a2c2a08f1aad.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164275877-09af936b-fb05-45b4-989d-484f41271725.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164275892-ea6f48d3-448b-49ba-9e08-f8e01043bec9.jpg" width="33%">
<br>
 
 #### 1. SignInActivity, SignUpActivity TextView 분기처리
 > SignInActivity, SignUpActivity
 ``` kotlin
 val etId = etSigninId.text.toString()
 val etPw = etSigninPw.text.toString()

 if (etId.isEmpty() || etPw.isEmpty()) {
     shortToast("로그인 실패")
 } else {
     shortToast("${etId}님 환영합니다")
 }
 ```
 ``` kotlin
 val etName = etSignupName.text.toString()
 val etId = etSignupId.text.toString()
 val etPw = etSignupPw.text.toString()

 if (etId.isEmpty() || etPw.isEmpty() || etName.isEmpty()) {
     shortToast("입력되지 않은 정보가 있습니다")
 } else {
     shortToast("회원가입이 완료되었습니다") 
 }
 ```
 #### 2. 토스트 메시지 출력
 > util 추가
 ``` kotlin
 fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
 }
 ```
 ``` kotlin
 shortToast("로그인 실패")
 ```
 #### 3. EditTextView 입력 내용 숨기기
 ``` kotlin
 android:inputType="textPersonName"
 ```
 #### 4. EditTextView 미리보기
 ``` kotlin
 android:hint="@string/et_signin_id"
 ```
 #### 5. 버튼 클릭리스너
 > SignInActivity, SignUpActivity
 ``` kotlin
 btnSigninLogin.setOnClickListener {
         startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
 }
 ```
 ``` kotlin
 btnSignUpLogin.setOnClickListener {
         finish()
 }
 ```
 #### 6. 자기소개 페이지 만들기
 <img src="https://user-images.githubusercontent.com/81347125/167310597-b3136478-edf6-4a5a-9500-9262c5ccadf9.png" width = "20%">
 
---


### &#10004; 성장과제 : 화면이동 + @, 사진 1:1 비율 및 스크롤 구현
<img src="https://user-images.githubusercontent.com/81347125/164283543-546238d9-593a-44f3-81b8-27174ba3d30c.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164283553-a906587e-8c60-4fb1-bd25-bd767869635f.jpg" width = "33%"> 
<br>
 
 #### 1. 회원가입 성공 시, 입력했던 아이디 및 패스워드 로그인 뷰로 이동(registerForActivityResult)
 > 1. SignInActivity에서 ActivityResult에 대한 콜백 등록 및 양방향 Launcher 생성
 ``` kotlin
 val resultLauncher =
     registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
         if (result.resultCode == Activity.RESULT_OK) {
             val myData: Intent? = result.data
             ...
         }
     }
 ```
 > 2. 위에 생성한 런처의 인자로 Intent를 담아, SignUpActivity 실행
 ``` kotlin
 btnSigninSignup.setOnClickListener {
     resultLauncher.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
 }
 ```
 > 3. SignUpActivity에서 putExtra를 통해 변수에 아이디 및 비밀번호 값을 담고, SignUpActivity를 종료시킴
 ``` kotlin
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
 }
  ```
 > 4. SignInActivity로 돌아와, 콜백 확인 후, 해당 결과값을 TextView에 세팅
 ``` kotlin
 if (result.resultCode == Activity.RESULT_OK) {
     val myData: Intent? = result.data
     binding.etSigninId.setText(myData?.getStringExtra("id"))
     binding.etSigninPw.setText(myData?.getStringExtra("pw"))
 }
  ```
 
 #### 2. 스크롤 뷰 구현
 > HomeActivity.xml에 ScrollView 생성
  ``` kotlin
 <androidx.core.widget.NestedScrollView
      android:layout_width="match_parent"
      android:layout_height="match_parent"> 
  ...
 </androidx.core.widget.NestedScrollView>
  ```
  #### 3. 이미지 비율 1:1 설정
 > imageView 속성에 해당 코드 추가
 ``` kotlin
 app:layout_constraintDimensionRatio="1:1"
 ```
 
 ---
 
 
### &#10004; 도전과제 : DataBinding 및 MVVM 구현
<img src="https://user-images.githubusercontent.com/81347125/164284287-2224e365-c1ee-41a8-91c1-46770970ea4a.jpg" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/164284514-c3f22daf-7efe-46d2-8e56-44c50a24e244.jpg" width = "33%"> 
<br>
 
 #### 1. DataBinding을 사용해 데이터 넣기
 > 1. build.gradle 세팅
 ``` kotlin
 buildFeatures {
     dataBinding true
 }
 ```
 > 2. 레이아웃 파일 수정, 루트를 layout으로 변경, data 태그 추가, variable 설정
 ``` kotlin
 <data>

     <variable
         name="home"
         type="com.example.sehun.feature.home.HomeData" />
 </data>
 ```
 > 3. Data Class 생성
 ``` kotlin
 data class HomeData(
    ...
 )
 ```
 > 4. 데이터 바인딩 설정
 ``` kotlin
 private lateinit var binding: ActivityHomeBinding

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        dataBinding()
    }
 ```
 > 5. Data class 연결
 ``` kotlin
 private fun dataBinding() {
    binding.home = HomeData(
            ...
    )
 }
 ```
 > 6. 뷰에서 데이터 넣기
 ``` kotlin
 android:text="@{home.name}"
 android:text="@{home.mbti}"
 ...
 ```
 #### 2. DataBinding을 사용해 이미지 넣기
 > 1. BindingAdapter object, 함수 구현
  ``` kotlin
  @JvmStatic
  @BindingAdapter("imgResId") // 사용하게 될 attribute
  fun setImageResId(imageview: ImageView, resId: Int) {
      imageview.setImageResource(resId)
  }
  ```
> 2. 뷰에서 이미지 넣기
 ``` kotlin
 app:imgResId="@{home.resid}"
 ```
 
 ---
## &#128204; 추가자료
#### 1. ViewBinding과 DataBinding 더 [알아보기](https://s2ehun.tistory.com/)
#### 2. registerForActivityResult 더 [알아보기](https://s2ehun.tistory.com/)
