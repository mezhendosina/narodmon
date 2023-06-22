package ru.nm17.narodmon.appNarodMonApiClient.entities.nameSensor

data class NameSensorRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val name: String,
    val uuid: String
)