package hw_3

import java.time.LocalDate
import java.time.Period
import kotlin.math.floor

/**
Вы решили написать функцию, которая из количества секунд генерирует человекопонятное представление времени публикации, например:
publishedAgo: 30
результат: менее минуты назад
publishedAgo: 90
результат: минуту назад
publishedAgo: 360
результат: 6 минут назад
publishedAgo: 3600
результат: час назад
publishedAgo: 7200
результат: 2 часа назад
Реализуйте подобную функцию.
 */

fun main() {
    print(publishedAgo(86400))
}

fun publishedAgo(
    sec: Long = 30
): String
{
    val nowData = LocalDate.now()
    val thenData = nowData.minusDays((sec/60/60)/24)
    val min = (sec/60) - floor((sec/60/60).toDouble())*60
    val minString = if(min>0) "$min минут " else ""
    val hour = (sec/60/60) - floor((sec/60/60/24).toDouble())*24
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


