package ru.nm17.narodmon.ui.entities

data class AboutSensorUiEntity(
    val name: String,
    val days: List<AboutSensorDayUiEntity>,
)


data class AboutSensorDayUiEntity(
    val fromTime: String,
    val toTime: String,
    val values: List<AboutSensorValuesUiEntity>
)

data class AboutSensorValuesUiEntity(
    val time: String,
    val value: Int
)