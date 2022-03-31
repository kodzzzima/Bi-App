package com.example.biapp.data.local.sample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.biapp.data.models.VacancyItemEntity

@Dao
interface SampleDao {

    @Query("SELECT * FROM ${SampleEntity.TABLE_NAME}")
    suspend fun getAll(): List<SampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyItemEntity: VacancyItemEntity)

    @Query("SELECT * FROM ${VacancyItemEntity.TABLE_NAME}")
    suspend fun getAllVacancies(): List<VacancyItemEntity>
}