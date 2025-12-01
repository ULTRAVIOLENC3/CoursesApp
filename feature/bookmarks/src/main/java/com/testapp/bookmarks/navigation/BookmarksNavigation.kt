package com.testapp.bookmarks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testapp.bookmarks.BookmarksRoute
import kotlinx.serialization.Serializable

@Serializable object BookmarksRoute

fun NavGraphBuilder.bookmarksScreen() {
    composable<BookmarksRoute> {
        BookmarksRoute(
            onCourseDetailsClick = {}
        )
    }
}