//package com.example.sehun.feature.home
//
//class Adapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private var popUpCommentDelEvent: ((commentIdx: Int) -> Unit)? = null
//    fun setPopUpCommentDelEvent(listener: (commentIdx: Int) -> Unit) {
//        this.popUpCommentDelEvent = listener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
//            RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return ItemCommentParentNotHasRecommentBinding
//            .inflate(layoutInflater, parent, false)
//            .let { CommentViewHolder(it, popUpCommentDelEvent) }
//    }
//
//    class ViewHolder(
//        private val binding: ItemCommentParentNotHasRecommentBinding,
//        private val popUpCommentDelEvent: ((commentIdx: Int) -> Unit)? = null
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun onBind(commentResponse: CommentResponse) {
//            binding.commentResponsementResponse = commentResponse
//            binding.commentDeleteButton.setOnClickListener {
//                popUpCommentDelEvent?.invoke(commentResponse.comment.commentIdx)
//            }
//        }
//    }
//}
//
//class HomeFollowerAdapter(private val itemClick: (FollowerInformation) -> (Unit)) :
//    RecyclerView.Adapter<HomeFollowerAdapter.HomeFollowerViewHolder>() {
//
//    ....
//
//    class HomeFollowerViewHolder(
//        private val binding: ItemHomeFollowerBinding,
//        private val itemClick: (FollowerInformation) -> (Unit)
//    ) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(follower: FollowerInformation) {
//            binding.follower = follower
//            binding.root.setOnClickListener {
//                itemClick(follower)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
//            HomeFollowerViewHolder {
//        val binding = DataBindingUtil.inflate<ItemHomeFollowerBinding>(
//            LayoutInflater.from(parent.context),
//            R.layout.item_home_follower,
//            parent,
//            false
//        )
//        return HomeFollowerViewHolder(binding, itemClick)
//    }
//
//    ....
//}