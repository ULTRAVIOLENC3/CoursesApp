package com.testapp.coursesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.testapp.coursesapp.navigation.CoursesAppNavHost
import com.testapp.coursesapp.navigation.TopLevelDestination
import kotlin.reflect.KClass


// TODO: Вытащить отсюда логику навигации в отдельный класс CourseAppState
@Composable
fun CoursesApp(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isBottomBarVisible = TopLevelDestination.entries.any { destination ->
        currentDestination?.hasRoute(destination.route::class) == true
    }

    Scaffold(
    bottomBar = {
        if (isBottomBarVisible) {
            CoursesAppBottomBar(
                destinations = TopLevelDestination.entries,
                currentDestination = currentDestination,
                onNavigateToDestination = { destination ->
                    navController.navigate(destination.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
    ) { innerPadding ->
        CoursesAppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean {
    return this?.hierarchy?.any { it.hasRoute(route) } == true
}