package ru.nm17.narodmon.appNarodMonApiClient.entities.appInit

data class AppInitResponseEntity(
    val addr: String,
    val favorites: List<Any>,
    val lat: Double,
    val latest: String,
    val login: String,
    val lon: Double,
    val timestamp: Int,
    val types: List<AppInitTypeEntity>,
    val url: String,
    val vip: Int
)