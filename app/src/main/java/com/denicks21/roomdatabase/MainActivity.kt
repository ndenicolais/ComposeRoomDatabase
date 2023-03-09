package com.denicks21.roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.denicks21.roomdatabase.navigation.NavGraph
import com.denicks21.roomdatabase.navigation.NavScreens
import com.denicks21.roomdatabase.ui.composables.CustomDrawer
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.ui.theme.RoomDatabaseTheme
import com.denicks21.roomdatabase.viewmodels.HomeViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabaseTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setNavigationBarColor(
                        GreyDark,
                        darkIcons = false
                    )
                }
                val navController = rememberAnimatedNavController()
                Surface {
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    ModalDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContent = {
                            CustomDrawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                        navController.graph.startDestinationRoute?.let {
                                            popUpTo(NavScreens.UserListPage.route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    ) {
                        NavGraph(
                            navController,
                            homeViewModel,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                }
            }
        }
    }
}