package ru.nm17.narodmon.appNarodMonApiClient.entities.webcamsNearby

data class WebcamNearbyEntity(
    val distance: Double,
    val fav: Int,
    val id: Int,
    val image: String,
    val lat: Double,
    val location: String,
    val lon: Double,
    val my: Int,
    val name: String,
    val owner: String,
    val time: Int
)