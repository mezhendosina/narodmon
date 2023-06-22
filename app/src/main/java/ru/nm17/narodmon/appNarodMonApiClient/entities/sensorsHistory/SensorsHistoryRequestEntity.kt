package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsHistory

data class SensorsHistoryRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val offset: Int,
    val period: String,
    val uuid: String
)