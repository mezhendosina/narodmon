package ru.nm17.narodmon.ui.entities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SensorFilterUiEntity(
    // TODO: Можно попробовать объединить с db/SensorType.kt
    val stringRes: Int,
    val code: Int,
    var enabled: MutableState<Boolean> = mutableStateOf(false),
)
