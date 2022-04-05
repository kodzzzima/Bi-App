package com.example.biapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.biapp.data.local.sample.SampleDao
import com.example.biapp.data.local.sample.SampleEntity
import com.example.biapp.data.models.ResumeItemEntity
import com.example.biapp.data.models.UserEntity
import com.example.biapp.data.models.VacancyItemEntity

@Database(
    entities = [
        SampleEntity::class,
        VacancyItemEntity::class,
        ResumeItemEntity::class,
        UserEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSampleDao(): SampleDao
}