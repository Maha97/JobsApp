package com.maha.speerotest.data.repository

import com.maha.speerotest.data.local.JobsDao
import com.maha.speerotest.data.model.Job
import com.maha.speerotest.data.remote.ApiService
import com.maha.speerotest.util.NetworkConnectivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobsRepository@Inject constructor(
    private val apiService: ApiService,
    private val jobsDao: JobsDao,
    private val networkConnectivity: NetworkConnectivity
){
    suspend fun getJobs(): List<Job>? {

        if (networkConnectivity.isConnected()) {
            return getJobsFromApi()
        }

        return getJobsFromDb()
    }

    suspend fun getJobsFromApi(): List<Job>? {
        val results = apiService.getJobs(20).await().jobs

        if (results != null) {
            cacheResults(results)
        }
        return results
    }

    suspend fun cacheResults(results: List<Job>) = withContext(Dispatchers.IO) {
        for (result in results){
            jobsDao.insertJob(result)
        }

    }

    suspend fun getJobsFromDb() = withContext(Dispatchers.IO) {
        val result = jobsDao.queryJobs()

        getCachedJobs(result)
    }

    private fun getCachedJobs(result: List<Job>?): List<Job>?{
        return result

    }
}