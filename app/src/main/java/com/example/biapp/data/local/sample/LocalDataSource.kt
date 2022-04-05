package com.example.biapp.data.local.sample

import com.example.biapp.data.models.ResumeItemEntity
import com.example.biapp.data.models.UserEntity
import com.example.biapp.data.models.VacancyItemEntity
import javax.inject.Inject
import kotlin.math.log

interface LocalDataSource {
    suspend fun getAll(): List<SampleEntity>
    suspend fun insertVacancy(vacancyItemEntity: VacancyItemEntity)
    suspend fun getAllVacancies(): List<VacancyItemEntity>
    suspend fun getAllVacanciesByCreatorId(login: String): List<VacancyItemEntity>

    suspend fun insertResume(resumeItemEntity: ResumeItemEntity)
    suspend fun getAllResumes(): List<ResumeItemEntity>
    suspend fun getAllResumesByCreatorId(login: String): List<ResumeItemEntity>

    suspend fun insertUser(userEntity: UserEntity)
    suspend fun getUser(login: String): UserEntity?
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

    override suspend fun getAllVacanciesByCreatorId(login: String): List<VacancyItemEntity> {
        return sampleDao.getAllVacanciesByCreatorId(login)
    }


    override suspend fun insertResume(resumeItemEntity: ResumeItemEntity) {
        sampleDao.insertResume(resumeItemEntity)
    }

    override suspend fun getAllResumes(): List<ResumeItemEntity> {
        return sampleDao.getAllResumes()
    }

    override suspend fun getAllResumesByCreatorId(login: String): List<ResumeItemEntity> {
        return sampleDao.getAllResumesByCreatorId(login)
    }

    override suspend fun insertUser(userEntity: UserEntity) {
        sampleDao.insertUser(userEntity)
    }

    override suspend fun getUser(login: String): UserEntity? {
        return sampleDao.getUser(login)
    }
}