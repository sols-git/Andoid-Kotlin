package com.solovyevs.socnetwork_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Instant.now
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf(

            Post(0, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "Kotlin course start! (PostType.POST)", 0,0,0,false),
            Post(1, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "Great!  (PostType.REPOST)", 0,0,0,false, type = PostType.REPOST, repostId = 4),
            Post(2, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "Kotlin MeetUp! (PostType.EVENT)", 0,0,0,false, type = PostType.EVENT),
            Post(3, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "Welcome to Kotlin Course! (PostType.VIDEO)", 0,0,0,false, type = PostType.VIDEO,
                video = "https://www.youtube.com/watch?v=L9k_NdTaMeI",
                cover = "https://s3.amazonaws.com/geekbrains-uploads/geekbrains/public/ckeditor_assets/pictures/4051/content_1-mcu8j5-roudxiiyt1u1j1a.jpeg"),
            Post(4, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "Our network is growing! (PostType.ADVERT)", 0,0,0,false,type = PostType.ADVERT,
                adlink = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.sostav.ru%2Fapp%2Fpublic%2Fimages%2Fnews%2F2017%2F08%2F15%2Fcompressed%2F125.JPG&imgrefurl=https%3A%2F%2Fwww.sostav.ru%2Fpublication%2Fsevergrupp-alekseya-mordashova-investirovala-v-platformu-netologiya-grupp-27868.html&tbnid=kJra0XOqYSdZxM&vet=12ahUKEwi4srvtx-DnAhXFPpoKHWQ0CFYQMygIegUIARDmAQ..i&docid=K3sSqRbAPrwQrM&w=700&h=460&itg=1&q=%D0%BD%D0%B5%D1%82%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D1%8F%20%D0%BB%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%B8%D0%B9&ved=2ahUKEwi4srvtx-DnAhXFPpoKHWQ0CFYQMygIegUIARDmAQ",
                cover = "https://www.sostav.ru/app/public/images/news/2017/08/15/compressed/125.JPG"),
            Post(5, "https://info-hit.ru/upload/iblock/c40/onlayn_universitet_netologiya.jpg","Netology",
                "BigData MeetUp! (PostType.EVENT)", 0,0,0,false, type = PostType.EVENT,
                position = 55.703974.x(37.6218013), address = "Москва, Варшавское шоссе, д. 1, стр. 3, 3 этаж, офис B306-B308")
        )
        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }


    }
}

