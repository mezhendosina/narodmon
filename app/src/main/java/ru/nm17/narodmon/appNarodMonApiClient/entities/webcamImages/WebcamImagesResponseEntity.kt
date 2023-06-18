package ru.nm17.narodmon.appNarodMonApiClient.entities.webcamImages

data class WebcamImagesResponseEntity(
    val distance: Double,
    val id: Int,
    val images: List<WebcamImageEntity>,
    val location: String,
    val name: String
)