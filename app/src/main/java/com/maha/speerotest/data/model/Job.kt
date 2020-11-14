package com.maha.speerotest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class Job(
    @PrimaryKey
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "company_name") val company_name: String,
    @field:Json(name = "category") val category: String,
    @field:Json(name = "candidate_required_location") val candidate_required_location: String,
    @field:Json(name = "salary") val salary: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "company_logo_url") val company_logo_url: String
)