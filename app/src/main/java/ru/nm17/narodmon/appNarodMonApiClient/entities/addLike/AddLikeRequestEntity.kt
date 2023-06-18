package ru.nm17.narodmon.appNarodMonApiClient.entities.addLike

data class AddLikeRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val uuid: String
)