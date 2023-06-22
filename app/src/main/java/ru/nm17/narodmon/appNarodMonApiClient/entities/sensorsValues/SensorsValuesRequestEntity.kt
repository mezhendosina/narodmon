package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsValues

data class SensorsValuesRequestEntity(
    val api_key: String,
    val cmd: String,
    val sensors: List<Int>,
    val uuid: String
)