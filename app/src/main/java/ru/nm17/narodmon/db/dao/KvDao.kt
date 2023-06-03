package ru.nm17.narodmon.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ru.nm17.narodmon.db.entities.KVSetting

@Dao
interface KvDao {
    @Query("SELECT * FROM settings")
    fun getAll(): List<KVSetting>

    @Query("SELECT * FROM settings WHERE setting_key = :key")
    fun getByKey(key: String): KVSetting?

    @Upsert
    fun setAll(vararg settings: KVSetting)

    @Delete
    fun delete(setting: KVSetting)
}
