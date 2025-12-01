package com.testapp.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.testapp.auth.navigation.LoginRoute
import com.testapp.auth.navigation.loginScreen
import com.testapp.bookmarks.navigation.bookmarksScreen
import com.testapp.main.navigation.MainRoute
import com.testapp.main.navigation.mainScreen
import com.testapp.profile.navigation.profileScreen

@Composable
fun CoursesAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute,
        modifier = modifier
    ) {
        loginScreen(
            onNavigateToMain = {
                navController.navigate(route = MainRoute) {
                    popUpTo(LoginRoute) {
                        inclusive = true
                    }
                }
            }
        )
        
        mainScreen(
            onNavigateToCourse = { }
        )

        bookmarksScreen()

        profileScreen()
    }
}