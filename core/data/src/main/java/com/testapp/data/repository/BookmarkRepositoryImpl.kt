package com.testapp.data.repository

import com.testapp.database.dao.BookmarkDao
import com.testapp.database.model.asEntity
import com.testapp.database.model.asExternalModel
import com.testapp.model.Course
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkRepository {
    override fun getBookmarks(): Flow<List<Course>> {
        return bookmarkDao.getAllBookmarks()
            .map { entities ->
                entities.map { it.asExternalModel() }
            }
    }

    override suspend fun addBookmark(course: Course) {

        bookmarkDao.insertBookmark(course.asEntity())
    }

    override suspend fun deleteBookmarkById(id: Int) {
        bookmarkDao.deleteBookmarkById(id)
    }
}