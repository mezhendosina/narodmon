package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsOnDevice

data class SensorsOnDeviceRequestEntity(
    val api_key: String,
    val cmd: String,
    val devices: List<Int>,
    val lang: String,
    val uuid: String
)