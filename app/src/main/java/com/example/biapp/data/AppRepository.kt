package com.example.biapp.data

import com.example.biapp.data.local.sample.LocalDataSource
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.data.models.UserItem
import com.example.biapp.domain.toEntity
import com.example.biapp.domain.toItem
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import javax.inject.Inject

interface AppRepository {
    suspend fun getAllLocal(): List<SampleItem>
    suspend fun getAllLocalVacancies(): List<VacancyItem>
    suspend fun insertLocalVacancy(vacancyItem: VacancyItem)
    suspend fun getAllLocalVacanciesByCreatorId(login: String): List<VacancyItem>

    suspend fun getAllLocalResumes(): List<ResumeItem>
    suspend fun insertLocalResume(resumeItem: ResumeItem)
    suspend fun getAllLocalResumesByCreatorId(login: String): List<ResumeItem>

    suspend fun getUser(login: String): UserItem
    suspend fun insertUser(userEntity: UserItem)

    suspend fun getVacanciesBySearch(searchQuery: String): List<VacancyItem>
}

class AppRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : AppRepository {
    override suspend fun getAllLocal(): List<SampleItem> =
        localDataSource.getAll().map { it.toItem() }

    override suspend fun getAllLocalVacancies(): List<VacancyItem> {
        return localDataSource.getAllVacancies().map { it.toItem() }
    }

    override suspend fun insertLocalVacancy(vacancyItem: VacancyItem) {
        localDataSource.insertVacancy(vacancyItem.toEntity())
    }

    override suspend fun getAllLocalVacanciesByCreatorId(login: String): List<VacancyItem> {
        return localDataSource.getAllVacanciesByCreatorId(login).map { it.toItem() }
    }

    override suspend fun getAllLocalResumes(): List<ResumeItem> {
        return localDataSource.getAllResumes().map { it.toItem() }
    }

    override suspend fun insertLocalResume(resumeItem: ResumeItem) {
        localDataSource.insertResume(resumeItem.toEntity())
    }

    override suspend fun getAllLocalResumesByCreatorId(login: String): List<ResumeItem> {
        return localDataSource.getAllResumesByCreatorId(login).map { it.toItem() }
    }

    override suspend fun getUser(login: String): UserItem {
        return localDataSource.getUser(login)?.toItem() ?: UserItem(
            id = -1,
            login = "-1",
            password = "-1",
        )
    }

    override suspend fun insertUser(userItem: UserItem) {
        localDataSource.insertUser(userItem.toEntity())
    }


    override suspend fun getVacanciesBySearch(searchQuery: String): List<VacancyItem> {
        return localDataSource.getVacanciesBySearch(searchQuery).map { it.toItem() }
    }
}