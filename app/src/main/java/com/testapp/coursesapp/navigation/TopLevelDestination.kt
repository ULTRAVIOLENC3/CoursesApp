package com.testapp.coursesapp.navigation

import androidx.annotation.DrawableRes
import com.testapp.bookmarks.navigation.BookmarksRoute
import com.testapp.designsystem.R
import com.testapp.main.navigation.MainRoute
import com.testapp.profile.navigation.ProfileRoute

enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    val title: String,
    val route: Any,
) {
    MAIN(
        selectedIcon = R.drawable.ic_house,
        title = "Главная",
        route = MainRoute,
    ),
    BOOKMARKS(
        selectedIcon = R.drawable.ic_bookmark,
        title = "Избранное",
        route = BookmarksRoute,
    ),
    PROFILE(
        selectedIcon = R.drawable.ic_person,
        title = "Аккаунт",
        route = ProfileRoute,
    ),
}