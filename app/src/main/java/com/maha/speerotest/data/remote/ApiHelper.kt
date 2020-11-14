package com.maha.speerotest.data.remote

import com.maha.speerotest.data.model.GetJobsBaseResponse
import kotlinx.coroutines.Deferred

interface ApiHelper{

    suspend fun getJobs(limit:Int):Deferred<GetJobsBaseResponse>

}