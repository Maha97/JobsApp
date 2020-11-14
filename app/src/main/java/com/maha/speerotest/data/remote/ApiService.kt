package com.maha.speerotest.data.remote

import com.maha.speerotest.data.model.GetJobsBaseResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


@InstallIn(ActivityComponent::class)
@Module
interface ApiService{

    @GET("remote-jobs")
    suspend fun getJobs(@Query("limit")  limit:Int): Deferred<GetJobsBaseResponse>
}