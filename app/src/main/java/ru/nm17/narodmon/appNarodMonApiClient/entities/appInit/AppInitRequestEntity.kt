package ru.nm17.narodmon.appNarodMonApiClient.entities.appInit

data class AppInitRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val platform: String,
    val utc: Int,
    val uuid: String,
    val version: String
)