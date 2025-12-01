package com.testapp.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testapp.main.MainRoute
import kotlinx.serialization.Serializable

@Serializable object MainRoute

fun NavGraphBuilder.mainScreen(
    onNavigateToCourse: (Int) -> Unit
) {
    composable<MainRoute> {
        MainRoute(onCourseDetailsClick = onNavigateToCourse)
    }
}