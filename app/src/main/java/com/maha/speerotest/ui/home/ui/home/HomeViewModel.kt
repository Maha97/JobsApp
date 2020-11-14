package com.maha.speerotest.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maha.speerotest.data.model.Job
import com.maha.speerotest.data.repository.JobsRepository
import com.maha.speerotest.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel (private  val jobsRepository: JobsRepository): ViewModel() {

    private var jobs = MutableLiveData<Resource<List<Job>>>()

    init{
        getJobs()
    }
    fun getJobs(){
        viewModelScope .launch{
            jobs.postValue(Resource.loading(null))
            try {
                val jobList = jobsRepository.getJobs()
                jobs.postValue(Resource.success(jobList))
            } catch (e: Exception) {
                Log.e("HomeViewModel",e.localizedMessage)

            }
        }
    }
    fun getJobsList(): LiveData<Resource<List<Job>>> {
        return jobs
    }



}