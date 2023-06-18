package ru.nm17.narodmon.appNarodMonApiClient.entities.weatherReport

data class WeatherReportRequestEntity(
    val api_key: String,
    val cmd: String,
    val humid: String,
    val lang: String,
    val lat: Double,
    val lon: Double,
    val press: String,
    val temp: String,
    val uuid: String,
    val wind: String
)