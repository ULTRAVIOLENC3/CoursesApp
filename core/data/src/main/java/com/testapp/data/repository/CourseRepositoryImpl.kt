package com.testapp.data.repository

import com.testapp.database.dao.BookmarkDao
import com.testapp.database.model.asEntity
import com.testapp.database.model.asExternalModel
import com.testapp.model.Course
import com.testapp.network.NetworkDataSource
import com.testapp.network.model.asExternalModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class CourseRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource,
    private val bookmarkDao: BookmarkDao
) : CourseRepository {

    override fun getCourses(): Flow<List<Course>> {
        val networkFlow = flow {
            val networkData = network.getCourses()
            val domainData = networkData.courses.map { it.asExternalModel() }
            emit(domainData)
        }.flowOn(Dispatchers.IO)

        val bookmarksFlow = bookmarkDao.getAllBookmarks().map { it.map { entity -> entity.asExternalModel() } }

        return networkFlow.combine(bookmarksFlow) { courses, bookmarks ->
            val bookmarkedIds = bookmarks.map { it.id }.toSet()
            courses.map { course ->
                course.copy(hasLike = bookmarkedIds.contains(course.id))
            }
        }
    }

    override fun getCourseById(id: Int): Flow<Course?> {
        TODO("Not yet implemented")
    }
}