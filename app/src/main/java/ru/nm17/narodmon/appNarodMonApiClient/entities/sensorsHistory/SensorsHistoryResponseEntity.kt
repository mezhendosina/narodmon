package ru.nm17.narodmon.appNarodMonApiClient.entities.sensorsHistory

data class SensorsHistoryResponseEntity(
    val `data`: List<HistoryDataEntity>,
    val sensors: List<HistorySensorEntity>
)