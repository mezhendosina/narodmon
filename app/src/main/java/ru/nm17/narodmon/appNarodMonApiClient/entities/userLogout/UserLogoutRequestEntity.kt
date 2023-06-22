package ru.nm17.narodmon.appNarodMonApiClient.entities.userLogout

data class UserLogoutRequestEntity(
    val api_key: String,
    val cmd: String,
    val uuid: String
)