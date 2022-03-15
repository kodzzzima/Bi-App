package com.example.biapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.biapp.data.local.sample.SampleDao
import com.example.biapp.data.local.sample.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSampleDao(): SampleDao
}