package ru.nm17.narodmon.appNarodMonApiClient.entities.webcamsNearby

data class WebcamsNearbyRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val lat: Double,
    val lon: Double,
    val radius: Int,
    val uuid: String
)