package ru.nm17.narodmon.appNarodMonApiClient.entities.sendCommand

data class SendCommandRequestEntity(
    val api_key: String,
    val cmd: String,
    val command: String,
    val id: Int,
    val uuid: String
)