package ru.nm17.narodmon.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KVSetting(
    @PrimaryKey val key: String,
    @ColumnInfo(name = "value") val value: String,
)
