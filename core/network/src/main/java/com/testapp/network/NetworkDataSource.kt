package com.testapp.network

import com.testapp.network.model.NetworkCourseResponse

interface NetworkDataSource {
    suspend fun getCourses(): NetworkCourseResponse
}