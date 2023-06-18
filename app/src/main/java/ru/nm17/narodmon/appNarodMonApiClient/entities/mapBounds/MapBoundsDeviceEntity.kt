package ru.nm17.narodmon.appNarodMonApiClient.entities.mapBounds

data class MapBoundsDeviceEntity(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val time: Int,
    val type: Int,
    val unit: String,
    val value: Double
)