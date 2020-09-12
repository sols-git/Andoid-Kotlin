package com.solovyevs.socnetwork_kotlin

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_card.view.*
import java.net.URL




class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(this, view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PostViewHolder) {
            bind(list[position])
        }
    }
}

class PostViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {


    init {
        with(itemView) {
            likeBtn.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    if(!item.isLike) {
                        item.countLikes += 1
                        item.isLike = true
                    }
                    else
                    {
                        item.countLikes -= 1
                        item.isLike = false
                    }
                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            commentsBtn.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.countComments+=1
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            shareBtn.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT, """
                                ${item.nameUser} (${item.datePost.toString()})
    
                                ${item.post}
                            """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    itemView.context.startActivity(intent)
                    item.countShare+=1
                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            pin.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val position = item.position!!
                    pin.setOnClickListener {
                        val lat = position.lat
                        val lon = position.lon
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse("geo:$lat,$lon")
                        }
                        itemView.context.startActivity(intent)
                    }
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            coverimg.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_VIEW
                        if(item.video != null)
                        {
                            data = Uri.parse(item.video)
                        }
                        if(item.adlink != null)
                        {
                            data = Uri.parse(item.adlink)
                        }

                    }
                    itemView.context.startActivity(intent)
                }
            }

            deletepost.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    adapter.notifyItemRemoved(adapterPosition)
                }
            }


        }
    }



    fun bind(post: Post) {
        with(itemView) {
            Picasso.get()
                .load(post.userlogo)
                .resize(150,150)
                .into(imgavatar)
            dataTv.text = post.datePost.toString()
            postTv.text = post.post
            userTv.text = post.nameUser
            if (post.countShare > 0) {
                count_shareTv.text = post.countShare.toString()
                count_shareTv.setTextColor(resources.getColor(R.color.colorRed))
                shareBtn.setImageResource(R.drawable.ic_share_red_24dp)

            }
            else
            {
                count_shareTv.text = ""
                shareBtn.setImageResource(R.drawable.ic_share_black_24dp)
            }
            if (post.countComments > 0) {
                count_commentsTv.text = post.countComments.toString()
                count_commentsTv.setTextColor(resources.getColor(R.color.colorRed))
                commentsBtn.setImageResource(R.drawable.ic_chat_bubble_red_24dp)

            }
            else
            {
                count_commentsTv.text = ""
                commentsBtn.setImageResource(R.drawable.ic_chat_bubble_black_24dp)
            }


            if (post.countLikes > 0) {
                count_likesTv.text = post.countLikes.toString()
                if (!post.isLike) {
                    count_likesTv.setTextColor(resources.getColor(R.color.colorGeneralText))
                    likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp)
                } else {
                    count_likesTv.setTextColor(resources.getColor(R.color.colorRed))
                    likeBtn.setImageResource(R.drawable.ic_favorite_red_24dp)
                }
            } else {
                count_likesTv.text = ""
                likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp)
            }

            initPostBYType(post, itemView)

        }
    }

    fun setImg(post: Post, view: View, imgId: ImageView)
    {
        if(post.cover != null)
        {
            view.coverimgRep.visibility = View.VISIBLE
            //val x = view.measuredHeight
            //val y = view.measuredWidth

            Picasso.get()
                .load(post.cover)
               //.resize(1000,700 )
                .into(imgId)
        }

    }



    fun initPostBYType(post: Post, view: View) = when (post.type)
    {
        PostType.POST -> {
            setImg(post, view, view.coverimg)

        }
        PostType.REPOST ->  {
            view.addLayout.visibility = View.VISIBLE

            val repostId = post.repostId
            var repost = adapter.list.get(repostId?:0)

            view.dataTvRep.text = repost.datePost.toString()
            view.postTvRep.text = repost.post
            view.userTvRep.text = repost.nameUser
            setImg(repost, view, view.coverimgRep)
            Picasso.get()
                .load(repost.userlogo)
                .resize(150,150)
                .into(view.imgavatarRep)

        }

        PostType.VIDEO -> {
            setImg(post, view, view.coverimg)
        }
        PostType.EVENT -> {
            setImg(post, view, view.coverimg)
            if(post.address != null)
            {
               view.pin.visibility = View.VISIBLE
               val a = view.postTv.text.toString()
                val b = post.address
               view.postTv.text = a.plus(" ").plus(b)

            }
            else{}
        }
        PostType.ADVERT -> {
            setImg(post, view, view.coverimg)
        }
    }


}





//// пример программного создания
//  val wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT
//                val lParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
//                    wrapContent, wrapContent
//                )
//
//                val repostTv = TextView(view.context)
//                repostTv.setText(repost.post)
//                repostTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
//                repostTv.setTextColor(Color.GRAY)
//                view.repLayout ?.addView(repostTv)