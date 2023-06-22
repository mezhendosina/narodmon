package ru.nm17.narodmon.appNarodMonApiClient.entities.userLocation

data class UserLocationByCellRequestEntity(
    val api_key: String,
    val cells: List<CellEntity>,
    val cmd: String,
    val lang: String,
    val uuid: String
)