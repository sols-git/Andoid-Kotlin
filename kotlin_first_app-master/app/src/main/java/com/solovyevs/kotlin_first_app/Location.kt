package com.solovyevs.kotlin_first_app

class Location (val lat: Double, val lon: Double) {
}

fun Double.x(that: Double) = Location(this, that)