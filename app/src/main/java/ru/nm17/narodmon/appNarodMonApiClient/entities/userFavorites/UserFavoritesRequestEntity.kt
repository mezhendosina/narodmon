package ru.nm17.narodmon.appNarodMonApiClient.entities.userFavorites

data class UserFavoritesRequestEntity(
    val api_key: String,
    val cmd: String,
    val lang: String,
    val sensors: List<Int>,
    val uuid: String,
    val webcams: List<Int>
)