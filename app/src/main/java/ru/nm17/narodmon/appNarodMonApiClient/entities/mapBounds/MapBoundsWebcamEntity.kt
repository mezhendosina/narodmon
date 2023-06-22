package ru.nm17.narodmon.appNarodMonApiClient.entities.mapBounds

data class MapBoundsWebcamEntity(
    val id: Int,
    val image: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val time: Int
)