package com.example.biapp.di

import android.content.Context
import androidx.room.Room
import com.example.biapp.core.db.AppDatabase
import com.example.biapp.data.local.sample.SampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "App-db"
        ).build()

    @Provides
    @Singleton
    fun provideSampleDao(db: AppDatabase): SampleDao = db.getSampleDao()
}