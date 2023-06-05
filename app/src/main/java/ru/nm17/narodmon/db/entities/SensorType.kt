package ru.nm17.narodmon.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SensorType(
    @PrimaryKey val code: Long,
    val name: String,
    val unit: String,
)
