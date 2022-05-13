# &#128154; Android-SeHun

## &#128204; 3 week

| Task           |1 week|2 week|3week|4 week|5 week|6 week|7 week|
|----------------|---------------|---------------|----------------|-----------|-----------|-----------|-----------|
| 필수 과제 | <ul><li> [x] 1-1</li><li> [x] 1-2</li><li> [x] 1-3 | <ul><li> [x] 1-1</li><li> [x] 1-2</li> |　- |　- |　- |　- | 　- |
| 성장 과제 | <ul><li> [x] 2-1</li><li> [x] 2-2 | <ul><li> [x] 2-1 |　- | 　- |　- |　- |　- |
| 도전 과제 | <ul><li> [x] 3-1</li> |　- |　- | 　- |　- |　- |　- |

<br/>

## &#128204; 구현 결과

<img src="https://user-images.githubusercontent.com/81347125/167109903-d18f55f1-ec5f-4558-a3ad-17d20706f196.gif" width="25%">
<br>

## &#128204; 과제 리뷰

### &#10004; 필수과제 : FollowerFragment, RepositoryFragment, RecyclerView 구현

<img src="https://user-images.githubusercontent.com/81347125/167101051-5d32e5c2-41f0-445a-981d-0727276ceae6.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101048-0037fd6b-d06f-4cc6-8ec8-44d387a2eff2.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101046-1126c3d9-8361-438e-8680-efe5c4dafbc3.png" width="33%">
<br>

#### 1. Activity에 Fragment 배치하기

> 1. HomeActivity.xml에 FragmentContainerView 배치

 ``` kotlin
 <androidx.fragment.app.FragmentContainerView
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
 ```

> 2. Fragment 2개 생성 후, HomeActivity와 Fragment 연동

 ``` kotlin
 private fun initTransactionEvent() {
     val followerFragment = FollowerFragment()
     val repositoryFragment = RepositoryFragment()
     ...
    }
 ```

> 3. FollowerFragment 디폴트로 설정

 ``` kotlin
supportFragmentManager.beginTransaction().add(R.id.fcv_home_swaplist, followerFragment).commit()
 ```

> 4. 버튼 분기 처리

 ``` kotlin
with(binding) {
    btnHomeFollowerlist.setOnClickListener {
        supportFragmentManager.beginTransaction().replace(
            R.id.fcv_home_swaplist,
            followerFragment
        ).commit()
    }

    btnHomeRepositorylist.setOnClickListener {
        supportFragmentManager.beginTransaction().replace(
            R.id.fcv_home_swaplist,
            repositoryFragment
        ).commit()
    }
}
 ```

#### 2. RecyclerView 구현하기

> 1. FollowerFragment.xml에 RecyclerView 배치(RepositoryFragment 동일)

 ``` kotlin
 <androidx.recyclerview.widget.RecyclerView
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />
 ```

> 2. RecyclerView에 들어갈 item_follower.xml 추가 및 디자인

 ``` kotlin
 <androidx.constraintlayout.widget.ConstraintLayout
     android:layout_width="match_parent"
     android:layout_height="wrap_content">
     ...
 </androidx.constraintlayout.widget.ConstraintLayout>
 ```

> 3. item_follower.xml에 루트를 layout으로 수정, data 태그 추가 및 variable 설정

 ``` kotlin
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
            name="follower"
            type="com.example.sehun.data.local.HomeFragmentData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:text="@{follower.name}" />

        <TextView
            android:text="@{follower.introduce}" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
 ```

> 4. DataClass 추가

 ``` kotlin
data class HomeFragmentData(
    val name: String,
    val introduce: String
)
 ```

> 5. RecyclerView Adapter 추가

 ``` kotlin
class FollowerAdapter(private val itemClick: (HomeFragmentData) -> Unit) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val itemList = mutableListOf<HomeFragmentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding,
        private val itemClick: (HomeFragmentData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeFragmentData) {
            binding.follower = data
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
 ```

> 6. RecyclerView 연동

 ``` kotlin
private fun initMainAdapter() {
    binding.rvFollowerList.adapter = followerAdapter
}
 ```

> 7. FollowerFragment에서 RecyclerView item에 데이터 삽입

 ``` kotlin
private fun addItemList() {
    followerAdapter.itemList.addAll(
        listOf<HomeFragmentData>(
            HomeFragmentData("권용민", "1111"),
            HomeFragmentData("김세훈", "2222"),
            HomeFragmentData("이종찬", "3333"),
            HomeFragmentData("이혜빈", "4444"),
            HomeFragmentData("최정원", "5555")
        )
    )
}
 ```

> 8. RepositoryFragment의 RecyclerView는 Grid Layout으로 디자인

 ``` kotlin
 app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
 ```

#### 3. 텍스트 보기 제한하기

> ellipsize 속성 추가, 텍스트가 잘릴 시 '...'로 표기

 ``` kotlin
 android:ellipsize="end"
 ```

> maxLines 속성 추가, 텍스트 최대 1줄 표기

 ``` kotlin
 android:maxLines="1"
 ```

---

### &#10004; 성장과제 : Intent 전달, RecyclerView item Event 및 ItemDecoration 구현

<img src="https://user-images.githubusercontent.com/81347125/167101041-465064d0-2caa-4178-8149-93db80038127.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101036-348e2e96-9e72-4840-92d0-2a7e353d02b3.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167101026-f49cff09-7b41-42d4-b81d-e8d999aa6edc.png" width="33%">
<br>

#### 1. RecyclerView 아이템 클릭 시, 해당 아이템의 이름과 설명 값을 DetailActivity에서 보여주기

> 1. FollowerAdapter의 viewHolder 클래스 내부 onBind함수에 아이템 클릭리스너 구현

 ``` kotlin
fun onBind(data: HomeFragmentData) {
    binding.follower = data
    binding.root.setOnClickListener {
        itemClick(data)
    }
}
 ```

> 2. FollowerFragment에서 putExtra를 이용해, Intent에 값을 담고 넘겨줌

 ``` kotlin
private fun getInfo() {
    followerAdapter = FollowerAdapter {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.apply {
            putExtra("name", it.name)
            putExtra("introduce", it.introduce)
        }
        startActivity(intent)
    }
}
 ```

> 3. Intent 값을 받고, 텍스트 뷰 세팅

 ``` kotlin
private fun setInfo() {
    val name = intent.getStringExtra("name")
    val intro = intent.getStringExtra("introduce")
    binding.tvDetailName.text = name.toString()
    binding.tvDetailDetailintro.text = intro.toString()
}
 ```

---

### &#10004; 도전과제 : 보일러 플레이트 코드 및 notifyDataSetChanged 개선방안

<img src="https://user-images.githubusercontent.com/81347125/167100998-7f1b1e02-c798-43c4-9a42-736913aa13b6.png" width = "33%"> <img src="https://user-images.githubusercontent.com/81347125/167100964-20953aef-21e9-43a0-961d-5bff0e6e4f07.png" width = "33%">
<br>

---

## &#128204; 추가자료

#### 1. RecyclerView 더 [알아보기](https://s2ehun.tistory.com/)

#### 2. notifyDataSetChanged 더 [알아보기](https://s2ehun.tistory.com/)
