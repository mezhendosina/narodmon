package ru.nm17.narodmon.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class KVSetting(
    @PrimaryKey val setting_key: String,
    @ColumnInfo(name = "setting_value") val value: String,
)
