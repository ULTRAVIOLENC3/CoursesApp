package com.testapp.network.demo

import android.content.Context
import com.testapp.network.NetworkDataSource
import com.testapp.network.model.NetworkCourseResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class DemoCoursesAppNetworkDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkJson: Json
) : NetworkDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getCourses(): NetworkCourseResponse {
        return context.assets.open("courses.json").use { inputStream ->
            networkJson.decodeFromStream(inputStream)
        }
    }
}