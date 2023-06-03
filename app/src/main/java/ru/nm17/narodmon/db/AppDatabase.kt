package ru.nm17.narodmon.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nm17.narodmon.db.dao.KvDao
import ru.nm17.narodmon.db.entities.KVSetting

@Database(entities = [KVSetting::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kvDao(): KvDao
}
