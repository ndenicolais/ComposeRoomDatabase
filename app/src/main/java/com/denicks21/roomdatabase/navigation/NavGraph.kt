package com.denicks21.roomdatabase.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.denicks21.roomdatabase.screen.*
import com.denicks21.roomdatabase.viewmodels.HomeViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost  
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = NavScreens.IntroPage.route
    )
    {
        composable(route = NavScreens.IntroPage.route) {
            IntroPage(navController)
        }
        composable(route = NavScreens.UserListPage.route) {
            UserListPage(navController, homeViewModel, openDrawer)
        }
        composable(route = NavScreens.UserAddPage.route) {
            UserAddPage(navController, homeViewModel, openDrawer)
        }
        composable(route = NavScreens.UserUpdatePage.route + "/{usId}/{isEdit}",
            arguments = listOf(
                navArgument("usId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ), enterTransition = {
                slideInVertically(
                    initialOffsetY = { 1800 }
                )
            }, popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { 1800 }
                )
            }
        ) {
            val isEdit = it.arguments?.getBoolean("isEdit")
            val usId = it.arguments?.getString("usId")
            UserUpdatePage(navController, homeViewModel, openDrawer, usId, isEdit!!)
        }
        composable(route = NavScreens.UserDetailsPage.route + "/{usId}",
            arguments = listOf(
                navArgument("usId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            val usId = it.arguments?.getString("usId")
            UserDetailsPage(navController, homeViewModel, openDrawer, usId)
        }
        composable(route = NavScreens.InfoPage.route) {
            InfoPage(navController, openDrawer)
        }
    }
}