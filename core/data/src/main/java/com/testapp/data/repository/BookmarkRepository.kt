package com.testapp.data.repository

import com.testapp.model.Course
import kotlinx.coroutines.flow.Flow


interface BookmarkRepository {
    fun getBookmarks(): Flow<List<Course>>
    suspend fun addBookmark(course: Course)
    suspend fun deleteBookmarkById(id: Int)
}