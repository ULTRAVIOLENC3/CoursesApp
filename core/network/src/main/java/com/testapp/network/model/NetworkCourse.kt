package com.testapp.network.model

import com.testapp.model.Course
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCourseResponse(val courses: List<NetworkCourse>)

@Serializable
data class NetworkCourse(
    val id: Int = 0,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

fun NetworkCourse.asExternalModel(): Course =
    Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate,
    )