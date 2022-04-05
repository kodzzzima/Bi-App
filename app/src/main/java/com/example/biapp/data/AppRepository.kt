package com.example.biapp.data

import com.example.biapp.data.local.sample.LocalDataSource
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.domain.toEntity
import com.example.biapp.domain.toItem
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import javax.inject.Inject

interface AppRepository {
    suspend fun getAllLocal(): List<SampleItem>
    suspend fun getAllLocalVacancies(): List<VacancyItem>
    suspend fun insertLocalVacancy(vacancyItem: VacancyItem)

    suspend fun getAllLocalResumes(): List<ResumeItem>
    suspend fun insertLocalResume(resumeItem: ResumeItem)
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


    override suspend fun getAllLocalResumes(): List<ResumeItem> {
        return localDataSource.getAllResumes().map { it.toItem() }
    }

    override suspend fun insertLocalResume(resumeItem: ResumeItem) {
        localDataSource.insertResume(resumeItem.toEntity())
    }
}