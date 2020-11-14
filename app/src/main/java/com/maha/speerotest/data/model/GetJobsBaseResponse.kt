package com.maha.speerotest.data.model

import com.squareup.moshi.Json

data class GetJobsBaseResponse( @field:Json(name="0-legal-notice") val legal_notice : String,
                                @field:Json(name="job-count") val job_count : Int,
                                @field:Json(name="jobs") val jobs : List<Job>)