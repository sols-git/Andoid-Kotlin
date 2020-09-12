package com.solovyevs.socnetwork_kotlin

class Location (val lat: Double, val lon: Double) {
}

fun Double.x(that: Double) = Location(this, that)