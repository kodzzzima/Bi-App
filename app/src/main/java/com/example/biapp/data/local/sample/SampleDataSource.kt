package com.example.biapp.data.local.sample

import javax.inject.Inject

interface SampleDataSource {
    suspend fun getAll(): List<SampleEntity>
}

class SampleDataSourceImpl @Inject constructor(
    private val sampleDao: SampleDao,
) : SampleDataSource {

    override suspend fun getAll(): List<SampleEntity> =
        sampleDao.getAll()
}