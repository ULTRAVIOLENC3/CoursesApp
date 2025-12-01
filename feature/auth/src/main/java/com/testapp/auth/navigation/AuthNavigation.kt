package com.testapp.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testapp.auth.LoginRoute
import kotlinx.serialization.Serializable

@Serializable object LoginRoute

fun NavGraphBuilder.loginScreen(
    onNavigateToMain: () -> Unit
) {
    composable<LoginRoute> {
        LoginRoute(onLoginClick = onNavigateToMain)
    }
}