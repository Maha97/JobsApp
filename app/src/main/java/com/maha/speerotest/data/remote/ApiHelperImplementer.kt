package com.maha.speerotest.data.remote

import com.maha.speerotest.data.remote.RetrofitBuilder.apiService

class ApiHelperImplementer (private  val apiService: ApiService):ApiHelper{

    override suspend fun getJobs(limit: Int) =apiService.getJobs(10)

}