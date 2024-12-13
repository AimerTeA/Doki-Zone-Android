package com.example.dokizone.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dokizone.data.local.db.DokiZoneDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): DokiZoneDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DokiZoneDatabase::class.java,
            "doki_zone_database"
        ).build()
    }

    @Provides
    fun provideRandomAnimeDao(database: DokiZoneDatabase) = database.randomAnimeDao()
}