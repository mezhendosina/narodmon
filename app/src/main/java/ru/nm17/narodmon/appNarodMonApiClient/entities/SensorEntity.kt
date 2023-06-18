package ru.nm17.narodmon.appNarodMonApiClient.entities


data class Sensor(
    val changed: Int,
    val fav: Int,
    val id: Int,
    val mac: String,
    val name: String,
    val pub: Int,
    val time: Int,
    val trend: Int,
    val type: Int,
    val unit: String,
    val value: Double
)