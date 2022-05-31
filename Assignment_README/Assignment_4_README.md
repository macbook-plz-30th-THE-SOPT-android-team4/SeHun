# &#128154; Android-SeHun

## &#128204; 4 week

| Task           |1 week|2 week|3week|4 week|5 week|6 week|7 week|
|----------------|---------------|---------------|----------------|-----------|-----------|-----------|-----------|
| 필수 과제 | <li> [x] LV 1-1</li><li> [x] LV 1-2</li><li> [x] LV 1-3 | <li> [x] LV 1-1</li><li> [x] LV 1-2</li> |<li> [x] LV 1</li> |<li> [x] LV 1</li> |　-|　-|　-|
| 성장 과제 | <li> [x] LV 2-1</li><li> [x] LV 2-2 | <li> [x] LV 2-1 |<li> [x] LV 2</li> | <li> [x] LV 2-1</li><li> [x] LV 2-2 |　-|　-|　-|
| 도전 과제 | <li> [x] LV 3-1</li> |　-|<li> [x] LV 3</li> |　-|　-|　-|　-|

<br/>

## &#128204; 구현 결과

<img src="https://user-images.githubusercontent.com/81347125/171039345-371901db-ff27-47ab-966f-4440d9228fae.gif" width="30%">                                              <img src="https://user-images.githubusercontent.com/81347125/171049202-94563256-f0a9-4a22-b6b2-48887961975e.PNG" width="46%">
<br>

## &#128204; 과제 리뷰

### &#10004; 필수과제 : 로그인 및 회원가입 서버통신 구현 [(SOPT API)](http://13.124.62.236/api-docs/)

<img src="https://user-images.githubusercontent.com/81347125/171039665-e93b3873-bab8-42fc-881c-21211c64cb3a.png" width = "60%">

<br>


#### 1. 라이브러리 추가 및 AndroidManifest 설정

> build.gradle(app) 세팅

 ``` kotlin
// 서버 연결을 위한 Retrofit2
implementation 'com.squareup.retrofit2:retrofit:2.9.0'

// Retrofit2에서 gson 사용을 위한 컨버터
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// gson 오픈소스 컨버터
implementation 'com.google.code.gson:gson:2.8.9'
 ```
 
 > AndroidManifest 세팅

 ``` kotlin
 <uses-permission android:name="android.permission.INTERNET"/>

// http통신 시 해당 속성 true로 변환, https 권장
android:usesCleartextTraffic="true"
 ```

#### 2. Request/Response 객체 설계(SignIn, SignUp 동일)

> Request Body, Json 객체의 키 값과 타입을 각각 data class 변수명, 타입과 일치 시킴

 ``` kotlin
data class RequestSignIn(
// JSON의 키 값과 data class의 변수명이 다른 경우, 자동으로 맵핑돼서 변환시켜줌
    @SerializedName("email") 
    val id: String,
    val password: String
)
 ```

> Response Body, Json 객체의 키 값과 타입을 각각 data class 변수명, 타입과 일치 시킴

 ``` kotlin
data class ResponseSignIn(
    val email: String,
    val name: String
)
 ```
 
 #### 3. Retrofit Interface 설계

> http 메소드 URI, 헤더 등 상호작용방법 정의, interface 생성 후 필요한 함수 구현

 ``` kotlin
interface SoptService {

    @POST("/auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseWrapper<ResponseSignUp>>

    @POST("/auth/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<ResponseWrapper<ResponseSignIn>>
}
 ```
 
 #### 4. Retrofit Interface 실제 구현체(객체) 만들기

> object Creator 생성, 최초 한번 메모리 할당(싱글톤), 이후 그 메모리에 인스턴스를 만들어 사용

 ``` kotlin
object SoptClient {
    private const val BASE_URL = "http://13.124.62.236"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
}
 ```

 #### 5. Callback 등록하여 통신 요청

> SignIn 콜백 구현부, Util을 이용해 코드 간소화

 ``` kotlin
private fun initNetwork() {
    val requestSignIn = RequestSignIn(
        id = binding.etSigninId.text.toString(),
        password = binding.etSigninPw.text.toString()
    )
    val call = SoptClient.soptService.postSignIn(requestSignIn)

    call.enqueueUtil(onSuccess = {
        if (!it.success) {
            shortToast("잘못된 아이디 혹은 비밀번호 입니다!")
        } else shortToast("${it.data?.email}님 환영합니다!")
        startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
    })
}
 ```
 
 > SignUp 콜백 구현부

 ``` kotlin
private fun initNetwork() {
    val requestSignUp = RequestSignUp(
        name = binding.etSignupName.text.toString(),
        id = binding.etSignupId.text.toString(),
        password = binding.etSignupPw.text.toString()
    )
    val call = SoptClient.soptService.postSignUp(requestSignUp)

    call.enqueueUtil(onSuccess = {
        if (!it.success) {
            shortToast("중복된 아이디 혹은 비밀번호입니다!")
        } else shortToast("회원가입이 완료되었습니다!")
        passingIntent(requestSignUp.id)
    })
}
 ```

---

### &#10004; 성장과제 : GitHub API 연동 및 Wrapper Class 구현 [(GIT API)](https://docs.github.com/en/rest/users/followers#list-the-people-a-user-follows)

<img src="https://user-images.githubusercontent.com/81347125/171039664-5b7ee6d9-6abe-4646-94d4-698de98f931e.png" width = "45%"> <img src="https://user-images.githubusercontent.com/81347125/171039663-50e6a636-cba1-42c7-88ad-cbe12eff874a.png" width = "45%">
<br>

#### 1. Response 객체 설계

> GET을 요청하기에 RequestBody는 필요없음, 명세서에서 필요한 키 값만 추가

 ``` kotlin
 data class ResponseHome(
    val login: String,
    val avatar_url: String,
    val html_url: String,
    )
 ```
 
 #### 2. Git Interface 설계

> POST와 다르게 @Path parameters 필요

 ``` kotlin
interface GitService {
    @GET("/users/{username}/following")
    fun getGit(
        @Path("username") username: String
    ): Call<List<ResponseHome>>
}
 ```
 
 #### 3. 구현체(object) 설계
 ``` kotlin
object GitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val gitService: GitService = retrofit.create(GitService::class.java)
}
 ```

> 부모와 자식이 ScrollView가 되는 상황이라면, 부모.requestDisallowInterceptTouchEvent(true)를 통해 부모에게 TouchEvent를 빼앗기지 않도록 하는 메소드임

> NestedScrolableHost 레이아웃은 스크롤이 가능한 하나의 자식만 가질 수 있음

---

### &#10004; 도전과제 : 갤러리에서 이미지 호출하기

<img src="https://user-images.githubusercontent.com/81347125/171039661-1cd0eaa2-42cd-4d69-a7e1-08152588d813.png" width = "60%">
<br>



---

## &#128204; 추가자료

#### 1. Glide 더 [알아보기](https://s2ehun.tistory.com/)

#### 2. ViewPager2 중첩 스크롤 문제 더 [알아보기](https://s2ehun.tistory.com/)
