package ru.nm17.narodmon.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nm17.narodmon.db.dao.UserDao
import ru.nm17.narodmon.db.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
