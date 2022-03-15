package com.example.biapp.data

import com.example.biapp.data.local.sample.SampleDataSource
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.domain.toItem
import javax.inject.Inject

interface AppRepository {
    suspend fun getAllLocal(): List<SampleItem>
}

class AppRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource,
) : AppRepository {
    override suspend fun getAllLocal(): List<SampleItem> =
        sampleDataSource.getAll().map { it.toItem() }
}