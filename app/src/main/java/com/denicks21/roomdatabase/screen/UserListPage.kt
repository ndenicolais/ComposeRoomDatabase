package com.denicks21.roomdatabase.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.PersonAddAlt
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.navigation.NavScreens
import com.denicks21.roomdatabase.ui.composables.CustomToolbar
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.ui.theme.YellowDark
import com.denicks21.roomdatabase.viewmodels.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListPage(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
) {
    homeViewModel.getAllUsers()
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "User List",
                openDrawer
            )
        },
        content = {
            val userList: List<User> by homeViewModel.userList.observeAsState(initial = listOf())
            if (userList.isNotEmpty()) {
                Surface(
                    color = Color.White, modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 4.dp),
                        state = lazyListState
                    ) {
                        items(
                            items = userList
                        ) { us ->
                            UserCard(
                                user = us,
                                navController = navController
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "No users onboarded yet.",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        NavScreens.UserAddPage.route
                    )
                },
                backgroundColor = YellowDark,
                contentColor = GreyDark
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Image(
                        Icons.Outlined.PersonAddAlt,
                        contentDescription = "Add user",
                    )
                    AnimatedVisibility(
                        visible = !lazyListState.isScrollingUp()
                    ) {
                        Text(
                            text = "Add user",
                            modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun UserCard(user: User, navController: NavHostController) {
    val expanded = remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = YellowDark,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    navController.navigate(
                        NavScreens.UserDetailsPage.routeWithArgs(
                            user.userId.toString()
                        )
                    )
                }
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row {
                Image(
                    Icons.Outlined.ContactPage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50)),
                )
                Spacer(
                    modifier = Modifier.width(20.dp)
                )
                Column {
                    Text(
                        text = user.userName,
                        color = GreyDark,
                        fontSize = 20.sp,
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    if (expanded.value) {
                        Text(
                            text = user.userSurname,
                            color = GreyDark,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}