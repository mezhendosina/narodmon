package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsOnDevice

import ru.nm17.narodmon.ui.entities.SensorEntity

data class SensorOnDeviceEntity(
    val cmd: Int,
    val distance: Double,
    val id: Int,
    val info: String,
    val location: String,
    val mac: String,
    val my: Int,
    val name: String,
    val owner: String,
    val photo: String,
    val sensors: List<SensorEntity>,
    val site: String,
    val time: Int
)