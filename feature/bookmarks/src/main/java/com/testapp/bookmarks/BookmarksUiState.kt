package com.testapp.bookmarks

import com.testapp.model.CourseItem

data class BookmarksUiState(
    val courses: List<CourseItem> = emptyList()
)