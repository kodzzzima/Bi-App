package com.example.biapp.data.local.sample

import com.example.biapp.data.models.ResumeItemEntity
import com.example.biapp.data.models.VacancyItemEntity
import javax.inject.Inject

interface LocalDataSource {
    suspend fun getAll(): List<SampleEntity>
    suspend fun insertVacancy(vacancyItemEntity: VacancyItemEntity)
    suspend fun getAllVacancies(): List<VacancyItemEntity>

    suspend fun insertResume(resumeItemEntity: ResumeItemEntity)
    suspend fun getAllResumes(): List<ResumeItemEntity>
}

class LocalDataSourceImpl @Inject constructor(
    private val sampleDao: SampleDao,
) : LocalDataSource {

    override suspend fun getAll(): List<SampleEntity> =
        sampleDao.getAll()

    override suspend fun insertVacancy(vacancyItemEntity: VacancyItemEntity) {
        sampleDao.insertVacancy(vacancyItemEntity)
    }

    override suspend fun getAllVacancies(): List<VacancyItemEntity> {
        return sampleDao.getAllVacancies()
    }



    override suspend fun insertResume(resumeItemEntity: ResumeItemEntity) {
        sampleDao.insertResume(resumeItemEntity)
    }

    override suspend fun getAllResumes(): List<ResumeItemEntity> {
        return sampleDao.getAllResumes()
    }
}