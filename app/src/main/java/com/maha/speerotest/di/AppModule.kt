package com.maha.speerotest.di

import android.app.Application
import androidx.room.Room
import com.maha.speerotest.data.local.JobsDao
import com.maha.speerotest.data.remote.ApiService
import com.maha.speerotest.data.repository.JobsRepository
import com.maha.speerotest.util.NetworkConnectivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app


    @Provides
    @Singleton
    fun provideNetworkInfo(): NetworkConnectivity = NetworkConnectivity(app)

    @Provides
    fun providePostRepository(apiService: ApiService,jobsDao: JobsDao,networkConnectivity: NetworkConnectivity): JobsRepository {
        return JobsRepository(apiService,jobsDao,networkConnectivity)
    }


}