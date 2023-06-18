package ru.nm17.narodmon.appNarodMonApiClient.entities.webcamImages

data class WebcamImagesRequestEntity(
    val api_key: String,
    val cmd: String,
    val id: Int,
    val limit: Int,
    val uuid: String
)