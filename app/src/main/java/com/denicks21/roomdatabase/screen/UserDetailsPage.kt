package com.denicks21.roomdatabase.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardReturn
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.navigation.NavScreens
import com.denicks21.roomdatabase.navigation.NavScreens.UserDetailsPage.title
import com.denicks21.roomdatabase.ui.composables.CustomTopBar
import com.denicks21.roomdatabase.ui.theme.*
import com.denicks21.roomdatabase.viewmodels.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserDetailsPage(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
    usId: String?,
) {
    homeViewModel.findUserById(usId!!)
    val selectedUser = homeViewModel.foundUser.observeAsState().value
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopBar(
                title,
                openDrawer
            )
        }
    ) {
        if (selectedUser != null) {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = GreyLight,
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(140.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = "#${selectedUser.userId}",
                            color = GreyDark,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = selectedUser.userName,
                            color = GreyDark,
                            fontSize = 25.sp
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = selectedUser.userSurname,
                            color = GreyDark,
                            fontSize = 25.sp
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = selectedUser.userCity,
                            color = GreyDark,
                            fontSize = 22.sp
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = "${selectedUser.userPhone}",
                            color = GreyDark,
                            fontSize = 18.sp
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = selectedUser.userEmail,
                            color = GreyDark,
                            fontSize = 18.sp
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            if (showDialog.value) {
                                DeleteDialog(navController,
                                    homeViewModel,
                                    selectedUser,
                                    name = "Are you sure you want to delete the user " +
                                            "${selectedUser.userName} " +
                                            "${selectedUser.userSurname}?",
                                    showDialog = showDialog.value,
                                    onDismiss = { showDialog.value = false })
                            }
                            FloatingActionButton(
                                onClick = { showDialog.value = true },
                                backgroundColor = CancelColor,
                                contentColor = Color.White
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete user"
                                )
                            }
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(
                                        NavScreens.UserUpdatePage.route +
                                                "/" + selectedUser.userId +
                                                "/" + true
                                    )
                                },
                                backgroundColor = YellowDark,
                                contentColor = Color.Black
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Edit user"
                                )
                            }
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(
                                        NavScreens.UserListPage.route
                                    )
                                },
                                backgroundColor = GreyDark,
                                contentColor = Color.White
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardReturn,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteDialog(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    selectedUser: User,
    name: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = "Delete User",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = name,
                    fontSize = 18.sp
                )
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        homeViewModel.deleteUser(selectedUser)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ConfirmColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "YES"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = CancelColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "NO"
                    )
                }
            }
        )
    }
}