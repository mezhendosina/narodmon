package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsValues

data class SensorValueEntity(
    val changed: Int,
    val id: Int,
    val time: Int,
    val trend: Int,
    val type: Int,
    val value: Int
)