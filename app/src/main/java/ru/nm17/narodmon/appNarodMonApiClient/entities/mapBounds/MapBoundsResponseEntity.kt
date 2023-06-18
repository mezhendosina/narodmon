package ru.nm17.narodmon.appNarodMonApiClient.entities.mapBounds

data class MapBoundsResponseEntity(
    val devices: List<MapBoundsDeviceEntity>,
    val webcams: List<MapBoundsWebcamEntity>
)