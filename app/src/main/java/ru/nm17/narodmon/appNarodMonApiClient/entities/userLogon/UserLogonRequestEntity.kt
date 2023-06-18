package ru.nm17.narodmon.appNarodMonApiClient.entities.userLogon

data class UserLogonRequestEntity(
    val api_key: String,
    val cmd: String,
    val hash: String,
    val lang: String,
    val login: String,
    val uuid: String
)