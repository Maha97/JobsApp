package com.maha.speerotest.di

import android.content.Context
import androidx.room.Room
import com.maha.speerotest.data.local.JobDatabase
import com.maha.speerotest.data.local.JobsDao
import com.maha.speerotest.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideJobsDao(database: JobDatabase):JobsDao{
        return database.jobsDao()
    }
    @Provides
    fun provideUserDao(database: JobDatabase): UserDao {
        return database.userDao()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): JobDatabase {
        return Room.databaseBuilder(
            appContext,
            JobDatabase::class.java,
            "JobsDatabase.db"
        ).build()
    }

}