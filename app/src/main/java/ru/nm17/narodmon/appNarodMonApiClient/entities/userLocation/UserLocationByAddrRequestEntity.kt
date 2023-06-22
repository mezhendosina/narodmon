package ru.nm17.narodmon.appNarodMonApiClient.entities.userLocation

data class UserLocationByAddrRequestEntity(
    val addr: String,
    val api_key: String,
    val cmd: String,
    val lang: String,
    val uuid: String
)