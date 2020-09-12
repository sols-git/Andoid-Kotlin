package hw_1

import hw_1.model.Profile

/**
 * Создайте класс Profile, позволяющий хранить информацию о пользователе:
 * id, логин, Имя, Фамилию, Статус, Аватар
 * Создайте вычисляемое свойство fullname, с кастомным get, который возвращает Имя + " " + Фамилия.
 */

fun main() {
    val profile = Profile("+79166319698", "Ivan", "Ivan", "Ivanov", "single", "..//pic/12345.jpg")
    println(profile.fullName)
}
