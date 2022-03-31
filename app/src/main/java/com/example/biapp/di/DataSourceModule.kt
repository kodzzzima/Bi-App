package com.example.biapp.di

import com.example.biapp.data.AppRepository
import com.example.biapp.data.AppRepositoryImpl
import com.example.biapp.data.local.sample.LocalDataSource
import com.example.biapp.data.local.sample.LocalDataSourceImpl
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
        impl: LocalDataSourceImpl,
    ): LocalDataSource
}