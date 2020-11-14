package com.maha.speerotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maha.speerotest.data.model.Job
import com.maha.speerotest.data.model.User


@Database(
    entities = arrayOf(Job::class, User::class), version = 1
)
abstract class JobDatabase :RoomDatabase(){
    abstract fun jobsDao(): JobsDao
    abstract fun userDao(): UserDao


}