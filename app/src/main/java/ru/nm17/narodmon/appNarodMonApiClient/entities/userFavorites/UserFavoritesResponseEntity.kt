package ru.nm17.narodmon.appNarodMonApiClient.entities.userFavorites

data class UserFavoritesResponseEntity(
    val sensors: List<FavoriteSensorEntity>,
    val webcams: List<FavoriteWebcamEntity>
)