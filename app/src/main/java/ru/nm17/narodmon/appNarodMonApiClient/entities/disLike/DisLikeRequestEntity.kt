package ru.nm17.narodmon.appNarodMonApiClient.entities.disLike

data class DisLikeRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val uuid: String
)