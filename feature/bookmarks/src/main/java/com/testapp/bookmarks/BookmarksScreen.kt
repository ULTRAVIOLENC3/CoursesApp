package com.testapp.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.testapp.designsystem.theme.CoursesAppTheme
import com.testapp.designsystem.theme.Typography
import com.testapp.model.Course
import com.testapp.ui.CourseCard


@Composable
internal fun BookmarksRoute(
    viewModel: BookmarksViewModel = hiltViewModel(),
    onCourseDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookmarksScreen(
        uiState = uiState,
        onBookmarkClicked = { id -> viewModel.deleteBookmark(id) },
    )
}

@Composable
internal fun BookmarksScreen(
    uiState: BookmarksUiState,
    onBookmarkClicked: (Int) -> Unit,
    ) {
    Scaffold() { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        )
        {
            Text(
                text = "Избранное",
                style = Typography.titleLarge
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.courses,
                    key = { course -> course.id }
                ) { course ->
                    CourseCard(
                        course = course,
                        onDetailsClick = { },
                        onBookmarkClick = { onBookmarkClicked(course.id) },
                        modifier = Modifier.height(236.dp)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun BookmarksScreenPreview() {
    CoursesAppTheme {
        BookmarksScreen(
            uiState = BookmarksUiState(List(5) { intex ->
                Course(
                    id = intex,
                    rate = "4.9",
                    price = "999",
                    startDate = "22 мая 2024",
                    title = "Java разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, " +
                            "фреймворки Spring и Maven, работу с базами данных и API. " +
                            "Создайте свой собственный проект, собрав портфолио и став" +
                            " востребованным специалистом для любой IT компании.",
                    hasLike = true,
                    publishDate = ""
                )
            }
            ),
            onBookmarkClicked = {},
        )
    }
}