package com.maha.speerotest.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maha.speerotest.data.remote.ApiHelper
import com.maha.speerotest.data.repository.JobsRepository
import com.maha.speerotest.ui.home.ui.home.HomeViewModel

class ViewModelFactory(private val  jobsRepository: JobsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(jobsRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }

}