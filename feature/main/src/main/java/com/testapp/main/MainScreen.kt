    package com.testapp.main

    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.interaction.MutableInteractionSource
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.lazy.rememberLazyListState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonColors
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextField
    import androidx.compose.material3.TextFieldDefaults
    import androidx.compose.material3.ripple
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.alpha
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.input.ImeAction
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    import com.testapp.designsystem.R
    import com.testapp.designsystem.theme.CoursesAppTheme
    import com.testapp.designsystem.theme.Typography
    import com.testapp.model.Course
    import com.testapp.ui.CourseCard


    @Composable
    internal fun MainRoute(
        modifier: Modifier = Modifier,
        viewModel: MainViewModel = hiltViewModel(),
        onCourseDetailsClick: (Int) -> Unit,
    ) {

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        MainScreen(
            uiState = uiState,
            modifier = modifier,
            onCourseDetailsClick = { courseId -> onCourseDetailsClick(courseId) },
            onBookmarkClicked = { course -> viewModel.toggleBookmark(course) },
            onSortClicked = { viewModel.toggleSortOrder() }
        )
    }
    @Composable
    internal fun MainScreen(
        uiState: MainUiState,
        onCourseDetailsClick: (Int) -> Unit,
        modifier: Modifier = Modifier,
        onBookmarkClicked: (Course) -> Unit,
        onSortClicked: () -> Unit
    ) {
        val listState = rememberLazyListState()

        LaunchedEffect(uiState.sortAscending) {
            listState.animateScrollToItem(0)
        }


        Scaffold() { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    TextField(
                        value = "",
                        onValueChange = {  },
                        placeholder = {
                            Text(
                                text = "Search courses...",
                                modifier = Modifier.alpha(0.5f)
                            ) },
                        singleLine = true,
                        textStyle = Typography.bodyMedium,
                        shape = RoundedCornerShape(30.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                                ) },
                        modifier = Modifier.weight(1f)
                    )

                    Button(
                        onClick = { },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_filter),
                                contentDescription = "Filter",
                                modifier = Modifier.size(26.dp),
                            ) },
                        modifier = Modifier.size(56.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text(
                        text = "По дате добавления",
                        style = Typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                            ) { onSortClicked() }
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_down_up),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.courses,
                        key = { course -> course.id }
                    ) { course ->
                        CourseCard(
                            course = course,
                            onDetailsClick = { onCourseDetailsClick(course.id) },
                            onBookmarkClick = { onBookmarkClicked(course) },
                            modifier = Modifier.height(236.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview(widthDp = 360, heightDp = 800)
    @Composable
    fun MainScreenPreview() {
        CoursesAppTheme {
            MainScreen(
                uiState = MainUiState(List(5) { intex ->
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
                onCourseDetailsClick = {},
                onBookmarkClicked = {},
                modifier = Modifier,
                onSortClicked = {}
            )
        }
    }