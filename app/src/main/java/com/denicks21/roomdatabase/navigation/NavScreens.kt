package com.denicks21.roomdatabase.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreens(val title: String, val route: String, var icon: ImageVector) {
    object IntroPage : NavScreens(
        "Intro", "IntroPage", Icons.Default.Home
    )

    object UserListPage : NavScreens(
        "User List", "UserListPage", Icons.Default.Groups
    )

    object UserAddPage : NavScreens(
        "Add User", "UserAddPage", Icons.Default.Info
    )

    object UserUpdatePage : NavScreens(
        "Update User", "UserUpdatePage", Icons.Default.Info
    )

    object UserDetailsPage : NavScreens(
        "User Details", "UserDetailsPage", Icons.Default.Info
    )

    object InfoPage: NavScreens(
        "Info", "InfoPage", Icons.Default.Info
    )

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}