package com.pasandevin.android.android_mvvm_metadata_viewer.di

import android.app.Application
import androidx.room.Room
import com.pasandevin.android.android_mvvm_metadata_viewer.VideosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application, callback: VideosDatabase.Callback): VideosDatabase {
        return Room.databaseBuilder(application,VideosDatabase::class.java, "videos")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

}