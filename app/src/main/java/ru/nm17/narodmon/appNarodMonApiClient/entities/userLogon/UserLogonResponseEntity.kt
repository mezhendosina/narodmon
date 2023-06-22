package ru.nm17.narodmon.appNarodMonApiClient.entities.userLogon

data class UserLogonResponseEntity(
    val login: String,
    val tz: Int,
    val uid: Int,
    val vip: Int
)