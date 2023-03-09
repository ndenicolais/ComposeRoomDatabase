package com.denicks21.roomdatabase.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.navigation.NavScreens
import com.denicks21.roomdatabase.navigation.NavScreens.UserUpdatePage.title
import com.denicks21.roomdatabase.ui.composables.CustomTextField
import com.denicks21.roomdatabase.ui.composables.CustomTopBar
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.viewmodels.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserUpdatePage(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
    userToEdit: String?,
    isEdit: Boolean,
) {
    val context = LocalContext.current

    homeViewModel.findUserById(userToEdit!!)
    val selectedUser: User = homeViewModel.foundUser.observeAsState().value!!
    usId = selectedUser.userId.toString()
    usName = selectedUser.userName
    usSurname = selectedUser.userSurname
    usCity = selectedUser.userCity
    usPhone = selectedUser.userPhone.toString()
    usEmail = selectedUser.userEmail

    val scrollState = rememberScrollState()
    val isEdited = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopBar(
                title,
                openDrawer
            )
        },
        content = {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(state = scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp)
                    )
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = true,
                        labelString = "User ID",
                        inputWrapper = usId,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 5,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usId = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
                        labelString = "User Name",
                        inputWrapper = usName,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usName = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
                        labelString = "User Surname",
                        inputWrapper = usSurname,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usSurname = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
                        labelString = "User City",
                        inputWrapper = usCity,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usCity = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
                        labelString = "User Phone",
                        inputWrapper = usPhone,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usPhone = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
                        labelString = "User Email",
                        inputWrapper = usEmail,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited.value = true
                        usEmail = it
                    }
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    FloatingActionButton(
                        onClick = {
                            if (
                                isEdited.value
                            ) {
                                val user = User(
                                    id = selectedUser.id,
                                    userId = usId.trim().toLong(),
                                    userName = usName,
                                    userSurname = usSurname,
                                    userCity = usCity,
                                    userPhone = usPhone.toLong(),
                                    userEmail = usEmail
                                )
                                if (isEdit) {
                                    updateUserInDB(context, navController, user, homeViewModel)
                                    Toast.makeText(
                                        context, "User updated", Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate(
                                        NavScreens.UserListPage.route
                                    )
                                }
                            } else {
                                Toast.makeText(
                                    context, "Update at least one information", Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        backgroundColor = GreyDark,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Update user"
                        )
                    }
                }
            }
        }
    )
}

fun updateUserInDB(
    context: Context,
    navController: NavHostController,
    user: User,
    homeViewModel: HomeViewModel,
) {
    homeViewModel.updateUserDetails(user)
    navController.popBackStack()
}