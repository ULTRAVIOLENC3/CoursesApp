package com.testapp.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testapp.profile.ProfileRoute
import kotlinx.serialization.Serializable


@Serializable object ProfileRoute

fun NavGraphBuilder.profileScreen() {
    composable<ProfileRoute> {
        ProfileRoute()
    }
}