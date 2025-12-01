package com.testapp.data.repository

import com.testapp.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses(): Flow<List<Course>>
    fun getCourseById(id: Int): Flow<Course?>
}