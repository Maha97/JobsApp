package com.maha.speerotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maha.speerotest.data.model.Job
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
@Dao
interface JobsDao {

    @Query("SELECT * FROM Job")
    fun queryJobs(): List<Job>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: Job)
}