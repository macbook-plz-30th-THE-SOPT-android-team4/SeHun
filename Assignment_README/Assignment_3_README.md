# &#128154; Android-SeHun

## &#128204; 3 week

| Task           |1 week|2 week|3week|4 week|5 week|6 week|7 week|
|----------------|---------------|---------------|----------------|-----------|-----------|-----------|-----------|
| 필수 과제 | <li> [x] LV 1-1</li><li> [x] LV 1-2</li><li> [x] LV 1-3 | <li> [x] LV 1-1</li><li> [x] LV 1-2</li> |<li> [x] LV 1</li> |　-|　-|　-|　-|
| 성장 과제 | <li> [x] LV 2-1</li><li> [x] LV 2-2 | <li> [x] LV 2-1 |<li> [x] LV 2</li> |　-|　-|　-|　-|
| 도전 과제 | <li> [x] LV 3-1</li> |　-|<li> [x] LV 3</li> |　-|　-|　-|　-|

<br/>

## &#128204; 구현 결과

<img src="https://user-images.githubusercontent.com/81347125/169523368-0f123944-66d8-4520-b6a6-eb9684c2ab44.gif" width="60%">
<br>

## &#128204; 과제 리뷰

### &#10004; 필수과제 : Design(Font, ViewPager2, BottomNavigation, TabLayout, Glide) 적용

<img src="https://user-images.githubusercontent.com/81347125/169509966-1c8e9a33-f2db-44c9-b555-508f534a753e.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/169509963-7a23c8f1-7c9a-4dfc-9ab5-84d6b5da5cc6.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/169509955-a86d1753-9410-413a-881c-56499b80073e.png" width="33%">
<br>


#### 1. Font 적용

> 추후 textAppearance로 수정하기 위해, 따로 fontfamily를 만들지 않고, 바로 적용시킴

 ``` kotlin
  android:fontFamily="@font/notosanskr_bold"
 ```
 
#### 2. ViewPager2 및 BottomNavigation 구현

> 1. res에 menu타입 리소스 파일 생성 및 item 추가

 ``` kotlin
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/ic_union"
        android:title="@string/menu_profile" />
        ...
</menu>
 ```

> 2. HomeActivity에 ViewPager2 및 BottomNavi 배치

 ``` kotlin
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
   ... >

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_home_viewpager2"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        ... />


    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bnv_home"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/sopt_white"
        app:itemIconTint="@color/selector_menu_color"
        app:itemRippleColor="@color/sopt_main_purple"
        app:itemTextColor="@color/selector_menu_color"
        app:layout_constraintBottom_toBottomOf="parent"
        app:menu="@menu/menu_home" />

</androidx.constraintlayout.widget.ConstraintLayout>
 ```

> 3. ViewPagerAdapter 구현

 ``` kotlin
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
 ```
 
 > 4. ViewPagerAdapter 및 BottomNavi 연동

 ``` kotlin 
 private lateinit var viewPagerAdapter: ViewPagerAdapter
 
 private fun initAdapter() {
    val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
    viewPagerAdapter = ViewPagerAdapter(this)
    viewPagerAdapter.fragments.addAll(fragmentList)

    binding.vpHomeViewpager2.adapter = viewPagerAdapter
}

    private fun initBottomNavi() {
    with(binding) {
        vpHomeViewpager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bnvHome.menu.getItem(position).isChecked = true
            }
        })

        bnvHome.setOnItemSelectedListener { // BottomNavi는 setOnItemSelectedListener 메소드 이용
            when (it.itemId) {
                R.id.menu_profile -> {
                    vpHomeViewpager2.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    vpHomeViewpager2.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    vpHomeViewpager2.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}

companion object {
    const val FIRST_FRAGMENT = 0
    const val SECOND_FRAGMENT = 1
    const val THIRD_FRAGMENT = 2
}
 ```
 
 #### 3. TabLayout 구현
 
 > 1. HomeFragment에 TabLayout 배치 및 구성 요소 디자인

 ``` kotlin
<com.google.android.material.tabs.TabLayout
        android:id="@+id/tl_homefragment_follow"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="23dp"
        app:layout_constraintTop_toBottomOf="@+id/tv_homefragment_github"
        app:tabIndicatorColor="@color/sopt_main_purple"
        app:tabRippleColor="@color/sopt_main_purple"
        app:tabSelectedTextColor="@color/sopt_main_purple"
        app:tabTextAppearance="@style/tab_text"
        app:tabTextColor="@color/gray">
        ...
    </com.google.android.material.tabs.TabLayout>
 ```
 
 > 2. TabLayout과 연동할 ViewPagerAdapter 구현

 ``` kotlin
class TabViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()
    
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
 ```
 
 > 3. ViewPagerAdapter 및 TabLayout 연동

 ``` kotlin
 private lateinit var tabViewPagerAdapter: TabViewPagerAdapter
 
 private fun initAdapter() {
    val fragmentList = listOf(FollowFragment(), FollowingFragment())

    tabViewPagerAdapter = TabViewPagerAdapter(this)
    tabViewPagerAdapter.fragments.addAll(fragmentList)

    binding.vpHomefragmentViewpager2.adapter = tabViewPagerAdapter
    }

private fun initTabLayout() {
    val tabLabel = listOf("팔로잉", "팔로워")

    TabLayoutMediator(
        binding.tlHomefragmentFollow,
        binding.vpHomefragmentViewpager2
    ) { tab, position ->
        tab.text = tabLabel[position]
    }.attach()
    }
 ```
 
 #### 4. 프로필 사진 Glide 처리하기
 
 > 1. BuildGradle 수정

 ``` kotlin
 //glide
 implementation 'com.github.bumptech.glide:glide:4.13.0'
 annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
 ```
 
 > 2. BindingAdapter에 Glide함수 추가

 ``` kotlin
@JvmStatic
@BindingAdapter("imgGlide")
fun setGlideImage(imageview: ImageView, image: Int) {
    Glide.with(imageview.context)
        .load(image)
        .circleCrop()
        .into(imageview)
}
 ```
 
 > 3. 필요한 레이아웃에서 적용

 ``` kotlin
 app:imgGlide="@{home.resid}"
}
 ```
 
 > 4. Glide된 이미지 테두리 만들기(약간 야매..)

 ``` kotlin
 android:background="@drawable/circle_profile_border_2dp"
 android:padding="2dp"
 ```
---

### &#10004; 성장과제 : ViewPager2 중첩 스크롤 문제 해결하기

<img src="https://user-images.githubusercontent.com/81347125/169517178-f402fe4f-44ef-4c05-95e5-d33ce2940aa8.png" width = "40%">
<br>

#### 1. 방향이 동일한 ViewPager2 객체 내의 스크롤 뷰를 지원하기 위해 ViewPager2 객체의 requestDisallowInterceptTouchEvent()를 호출

> 1. [NestedScrollableHost](https://github.com/macbook-plz-30th-THE-SOPT-android-team4/SeHun/blob/main/app/src/main/java/com/example/sehun/util/NestedScrollableHost.kt) 추가

> 2. 필요한 자식 ViewPager2에 아래와 같이 추가

 ``` kotlin
 <com.example.sehun.util.NestedScrollableHost
        android:layout_width="match_parent"
        android:layout_height="0dp"
        ...>

        <androidx.viewpager2.widget.ViewPager2
           ... />

  </com.example.sehun.util.NestedScrollableHost>
 ```

> 부모와 자식이 ScrollView가 되는 상황이라면, 부모.requestDisallowInterceptTouchEvent(true)를 통해 부모에게 TouchEvent를 빼앗기지 않도록 하는 메소드임  

> NestedScrolableHost 레이아웃은 스크롤이 가능한 하나의 자식만 가질 수 있음

---

### &#10004; 도전과제 : 갤러리에서 이미지 호출하기

<img src="https://user-images.githubusercontent.com/81347125/169519541-3d3352d1-8600-4d31-8c50-7566498d00bc.png" width = "40%"> 
<br>


---

## &#128204; 추가자료

#### 1. RecyclerView 더 [알아보기](https://s2ehun.tistory.com/)

#### 2. notifyDataSetChanged 더 [알아보기](https://s2ehun.tistory.com/)
