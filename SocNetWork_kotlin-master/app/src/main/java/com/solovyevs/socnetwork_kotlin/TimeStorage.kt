package com.solovyevs.socnetwork_kotlin

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.math.floor

fun publishedAgo(
    sec: Long
): String
{
    val nowData = LocalDate.now()
    val thenData = nowData.minusDays((sec/60/60)/24)
    val min = (sec/60) - floor((sec/60/60).toDouble()) *60
    val minString = if(min>0) "$min минут " else ""
    val hour = (sec/60/60) - floor((sec/60/60/24).toDouble()) *24
    val hourString = if(hour>0) "$hour часов " else ""
    val days = Period.between(thenData, nowData).days
    val daysString = if(days>0) "$days дней " else ""
    val months = Period.between(thenData, nowData).months
    val monthsString = if(months>0) "$months месяцев " else ""
    val years = Period.between(thenData, nowData).years
    val yearsString = if(years>0) "$years лет " else ""
    val  timePublicate = "Время публикации менее $yearsString $monthsString $daysString $hourString $minString"
    return timePublicate
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}