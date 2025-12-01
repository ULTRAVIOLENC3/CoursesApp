package com.testapp.bookmarks

import com.testapp.model.Course

data class BookmarksUiState(
    val courses: List<Course> = emptyList()
)