package ru.nm17.narodmon.appNarodMonApiClient.entities.mapBounds

data class MapBoundsRequestEntity(
    val api_key: String,
    val bounds: List<Int>,
    val cmd: String,
    val lang: String,
    val limit: Int,
    val uuid: String
)