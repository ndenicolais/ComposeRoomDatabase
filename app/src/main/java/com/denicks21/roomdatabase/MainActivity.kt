package com.denicks21.roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.SideEffect
import com.denicks21.roomdatabase.screen.MainPage
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.ui.theme.RoomDatabaseTheme
import com.denicks21.roomdatabase.viewmodels.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

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
                MainPage(homeViewModel)
            }
        }
    }
}