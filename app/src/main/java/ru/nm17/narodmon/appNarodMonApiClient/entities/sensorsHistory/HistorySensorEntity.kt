package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsHistory

data class HistorySensorEntity(
    val id: Int,
    val name: String,
    val type: Int,
    val unit: String
)