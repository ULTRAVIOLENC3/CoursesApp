package com.testapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.data.repository.BookmarkRepository
import com.testapp.data.repository.CourseRepository
import com.testapp.model.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltViewModel
class MainViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _sortAscending = MutableStateFlow(true)

    val uiState: StateFlow<MainUiState> = combine(
        courseRepository.getCourses(),
        _sortAscending
    ) { courses, ascending ->
        val sorted = courses.sortedWith(compareBy { parseDate(it.publishDate) })

        MainUiState(
            courses = if (ascending) sorted else sorted.reversed(),
            sortAscending = ascending
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState()
    )

    fun toggleBookmark(course: Course) {
        viewModelScope.launch {
            val isBookmarked = course.hasLike

            if (isBookmarked) {
                deleteBookmark(course.id)
            } else {
                addBookmark(course)
            }
        }
    }

    private fun addBookmark(course: Course) {
        viewModelScope.launch {
            bookmarkRepository.addBookmark(
                course = course
            )
        }
    }

    private fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            bookmarkRepository.deleteBookmarkById(id)
        }
    }

    fun toggleSortOrder() {
        _sortAscending.value = !_sortAscending.value
    }

    private fun parseDate(dateStr: String): LocalDate {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.of("ru", "RU"))
            LocalDate.parse(dateStr, formatter)
        } catch (e: Exception) {
            LocalDate.MIN
        }
    }
}