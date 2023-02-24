package com.denicks21.roomdatabase.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.ui.composables.CustomMessage
import com.denicks21.roomdatabase.ui.composables.CustomTextField
import com.denicks21.roomdatabase.ui.composables.CustomToolbar
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.viewmodels.HomeViewModel

var usId: String = ""
var usName: String = ""
var usSurname: String = ""
var usCity: String = ""
var usPhone: String = ""
var usEmail: String = ""

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserAddPage(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
) {
    val context = LocalContext.current
    val message = remember { mutableStateOf(false) }

    clearAll()

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Add User",
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
                        modifier = Modifier.size(140.dp)
                    )
                    CustomMessage(message.value)
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false,
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
                        usEmail = it
                    }
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Button(
                        onClick = {
                                val user = User(
                                    id = usId.trim().toInt(),
                                    userId = usId.trim().toLong(),
                                    userName = usName,
                                    userSurname = usSurname,
                                    userCity = usCity,
                                    userPhone = usPhone.toLong(),
                                    userEmail = usEmail
                                )
                                addUserInDB(context, navController, user, homeViewModel)
                                clearAll()
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp)
                            .padding(bottom = 10.dp)
                    ) {
                        Text(
                            text = "Add",
                            modifier = Modifier.fillMaxWidth(),
                            color = GreyDark,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}

fun clearAll() {
    usId = ""
    usName = ""
    usSurname = ""
    usCity = ""
    usPhone = ""
}

fun addUserInDB(
    context: Context,
    navController: NavHostController,
    user: User,
    homeViewModel: HomeViewModel
) {
    homeViewModel.addUser(user)
    navController.popBackStack()
}

