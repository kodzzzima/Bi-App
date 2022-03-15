package com.example.biapp.di

import com.example.biapp.data.AppRepository
import com.example.biapp.data.AppRepositoryImpl
import com.example.biapp.data.local.sample.SampleDataSource
import com.example.biapp.data.local.sample.SampleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindAppRepository(
        impl: AppRepositoryImpl,
    ): AppRepository

    @Binds
    fun bindSampleDataSource(
        impl: SampleDataSourceImpl,
    ): SampleDataSource
}