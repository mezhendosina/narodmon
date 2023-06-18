package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsNearby

data class SensorsNearbyRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val lat: Double,
    val lon: Double,
    val radius: Int,
    val types: List<Int>,
    val uuid: String
)