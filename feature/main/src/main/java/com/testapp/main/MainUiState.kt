package com.testapp.main

import com.testapp.model.CourseItem
import java.util.Collections.emptyList

data class MainUiState(
    val courses: List<CourseItem> = emptyList()
)