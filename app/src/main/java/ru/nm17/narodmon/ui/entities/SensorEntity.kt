package ru.nm17.narodmon.ui.entities

import ru.nm17.narodmon.db.entities.SensorType


data class SensorEntity(
    // TODO: Вынести в отдельный класс, и явно не в директорию `ui`
    val id: Int,
    val type: SensorType,
    val deviceName: String,
    val deviceOwner: Int,
    val name: String,
    val favorite: Boolean,
    val public: Boolean,
    val mine: Boolean,
    val location: String,
    val distance: Double,  // километры
    val value: Double,
    val unit: String,
    val changed: Int,
)