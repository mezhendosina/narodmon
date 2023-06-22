package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsNearby

import ru.nm17.narodmon.appNarodMonApiClient.entities.SensorEntity


data class NearbyDeviceEntity(
    val cmd: Int,
    val distance: Double,
    val id: Int,
    val lat: Double,
    val location: String,
    val lon: Double,
    val mac: String,
    val my: Int,
    val name: String,
    val owner: String,
    val sensors: List<SensorEntity>,
    val time: Int
)