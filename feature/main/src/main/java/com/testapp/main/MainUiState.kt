package com.testapp.main

import com.testapp.model.Course
import java.util.Collections.emptyList

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val sortAscending: Boolean = false
)