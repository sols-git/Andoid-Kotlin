package com.solovyevs.socnetwork_kotlin

import java.time.LocalDate
import java.util.*

class Post (
    var id: Int,
    var userlogo: String,
    var nameUser: String,
    var post: String,
    var countLikes: Int,
    var countComments: Int,
    var countShare: Int,
    var isLike: Boolean,
    val type: PostType = PostType.POST,
    val position:Location? = null,
    val address: String? = null,
    val video: String? = null,
    val cover: String? = null,
    var repostId: Int? = null,
    var adlink: String? = null

) {
    var datePost: LocalDate = LocalDate.now()
}


enum class PostType {
    POST, REPOST, VIDEO, EVENT, ADVERT
}