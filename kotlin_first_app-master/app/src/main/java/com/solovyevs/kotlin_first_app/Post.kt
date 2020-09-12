package com.solovyevs.kotlin_first_app

import java.time.LocalDate
import java.util.*

class Post (
    var id: Int,
    var nameUser: String,
    var post: String,
    //private var datePost: LocalDate,
    var datePostTS: Long,
    var countLikes: Int,
    var countComments: Int,
    var countShare: Int,
    var isLike: Boolean


) {
}

class EventPost (
    var id: Int,
    var nameUser: String,
    var post: String,
    var datePostTS: Long,
    var countLikes: Int,
    var countComments: Int,
    var countShare: Int,
    var isLike: Boolean,
    val position:Location,
    val address: String


) {
}