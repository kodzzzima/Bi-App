package com.example.biapp.data.local.sample

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SampleDao {

    @Query("SELECT * FROM ${SampleEntity.TABLE_NAME}")
    suspend fun getAll(): List<SampleEntity>
}