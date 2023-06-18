package ru.nm17.narodmon.appNarodMonApiClient.entities.userLocation

data class UserLocationByCoordRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val lat: Double,
    val lon: Double,
    val uuid: String
)