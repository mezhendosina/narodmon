package ru.nm17.narodmon.appNarodMonApiClient.entities.userLocation

data class UserLocationByWifiRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val uuid: String,
    val wifi: List<WifiEntity>
)