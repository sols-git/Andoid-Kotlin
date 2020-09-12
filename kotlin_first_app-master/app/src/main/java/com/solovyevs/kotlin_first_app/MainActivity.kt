package com.solovyevs.kotlin_first_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var post = Post(
        1,
        "Vasya",
        "First post in our network!!!",
         600,
        0,
        0,
        0,
        false

    )

    var eventpost = EventPost(
        1,
        "Vasya",
        "Встреча в зимнем!!!",
        600,
        0,
        0,
        0,
        false,
        59.9403985.x(30.3116075),
        "Санкт-Петербург, Зимний дворец"

    )

    var videoPost = Post(
        2,
        "Нетология. Университет интернет профессий",
        "Иллюстрация в медиа. Приглашение на дизайн лекторий \n 8 353 просмотра",
        10000,
        100,
        20,
        50,
        false
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        update()
        updateEventPost()
        updateVideoPost()

    }

    fun update() {
        dataTv.text = publishedAgo(post.datePostTS)
        postTv.text = post.post
        userTv.text = post.nameUser
        if(post.countShare>0) {
            count_shareTv.text = post.countShare.toString()
            count_shareTv.setTextColor(resources.getColor(R.color.colorRed))
            shareBtn.setImageResource(R.drawable.ic_share_red_24dp)

        }
        if(post.countComments>0)
        {
            count_commentsTv.text = post.countComments.toString()
            count_commentsTv.setTextColor(resources.getColor(R.color.colorRed))
            commentsBtn.setImageResource(R.drawable.ic_chat_bubble_red_24dp)

        }


        if (post.countLikes>0) {
            count_likesTv.text = post.countLikes.toString()
            if(!post.isLike)
            {
                count_likesTv.setTextColor(resources.getColor(R.color.colorGeneralText))
                likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp)
            }
            else {
                count_likesTv.setTextColor(resources.getColor(R.color.colorRed))
                likeBtn.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }
        else
        {
            count_likesTv.text = ""
            likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp)
        }


    }


    fun updateEventPost() {
        dataTv3.text = publishedAgo(eventpost.datePostTS)
        postTv3.text = eventpost.post
        userTv3.text = eventpost.nameUser
        if(eventpost.countShare>0) {
            count_shareTv3.text = eventpost.countShare.toString()
            count_shareTv3.setTextColor(resources.getColor(R.color.colorRed))
            shareBtn3.setImageResource(R.drawable.ic_share_red_24dp)

        }
        if(eventpost.countComments>0)
        {
            count_commentsTv3.text = eventpost.countComments.toString()
            count_commentsTv3.setTextColor(resources.getColor(R.color.colorRed))
            commentsBtn3.setImageResource(R.drawable.ic_chat_bubble_red_24dp)

        }


        if (eventpost.countLikes>0) {
            count_likesTv3.text = eventpost.countLikes.toString()
            if(!eventpost.isLike)
            {
                count_likesTv3.setTextColor(resources.getColor(R.color.colorGeneralText))
                likeBtn3.setImageResource(R.drawable.ic_favorite_black_24dp)
            }
            else {
                count_likesTv3.setTextColor(resources.getColor(R.color.colorRed))
                likeBtn3.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }
        else
        {
            count_likesTv3.text = ""
            likeBtn3.setImageResource(R.drawable.ic_favorite_black_24dp)
        }




        val position = eventpost.position
        pin_event3.setOnClickListener {
            val lat = position.lat
            val lon = position.lon
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("geo:$lat,$lon")
            }
            startActivity(intent)
        }
    }

    fun updateVideoPost() {
        dataTv2.text = publishedAgo(videoPost.datePostTS)
        postTv2.text = videoPost.post
        userTv2.text = videoPost.nameUser
        if(videoPost.countShare>0) {
            count_shareTv2.text = videoPost.countShare.toString()
            count_shareTv2.setTextColor(resources.getColor(R.color.colorRed))
            shareBtn2.setImageResource(R.drawable.ic_share_red_24dp)

        }
        if(videoPost.countComments>0)
        {
            count_commentsTv2.text = videoPost.countComments.toString()
            count_commentsTv2.setTextColor(resources.getColor(R.color.colorRed))
            commentsBtn2.setImageResource(R.drawable.ic_chat_bubble_red_24dp)

        }


        if (videoPost.countLikes>0) {
            count_likesTv2.text = videoPost.countLikes.toString()
            if(!videoPost.isLike)
            {
                count_likesTv2.setTextColor(resources.getColor(R.color.colorGeneralText))
                likeBtn2.setImageResource(R.drawable.ic_favorite_black_24dp)
            }
            else {
                count_likesTv2.setTextColor(resources.getColor(R.color.colorRed))
                likeBtn2.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }
        else
        {
            count_likesTv2.text = ""
            likeBtn2.setImageResource(R.drawable.ic_favorite_black_24dp)
        }



    }

    fun clickLikes(view: View) {
        if(!post.isLike) {
            post.countLikes += 1
            post.isLike = true
        }
        else
        {
            post.countLikes -= 1
            post.isLike = false
        }
        update()
    }
    fun clickMessage(view: View) {
        post.countComments+=1
        update()
    }
    fun clickShare(view: View) {
        post.countShare+=1
        update()
    }

    fun clickShare2(view: View) {
        videoPost.countShare+=1
        updateVideoPost()
    }
    fun clickMessage2(view: View) {
        videoPost.countComments+=1
        updateVideoPost()
    }
    fun clickLikes2(view: View) {
        if(!videoPost.isLike) {
            videoPost.countLikes += 1
            videoPost.isLike = true
        }
        else
        {
            videoPost.countLikes -= 1
            videoPost.isLike = false
        }
        updateVideoPost()
    }

    fun onClickImgYoutube(view: View) {

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://www.youtube.com/watch?v=WhWc3b3KhnY")
        }
        startActivity(intent)
    }

    fun clickShare3(view: View) {
        eventpost.countShare+=1
        updateEventPost()
    }
    fun clickMessage3(view: View) {
        eventpost.countComments+=1
        updateEventPost()
    }
    fun clickLikes3(view: View) {
        if(!eventpost.isLike) {
            eventpost.countLikes += 1
            eventpost.isLike = true
        }
        else
        {
            eventpost.countLikes -= 1
            eventpost.isLike = false
        }
        updateEventPost()
    }


}
