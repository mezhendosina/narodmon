package ru.nm17.narodmon.appNarodMonApiClient.entities.userFavorites

data class FavoriteSensorEntity(
    val id: Int,
    val name: String,
    val time: Int,
    val type: Int,
    val value: Double
)