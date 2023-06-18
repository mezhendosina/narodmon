package ru.nm17.narodmon.appNarodMonApiClient.entities.pubSensor

data class PubSensorRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val uuid: String
)