package com.denicks21.roomdatabase.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.roomdatabase.R
import com.denicks21.roomdatabase.ui.composables.CustomToolbar
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.ui.theme.GreyLight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoPage(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Info application",
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
                        .padding(10.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = GreyLight,
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.app_name
                                ),
                                color = GreyDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                modifier = Modifier.clip(CircleShape)
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Divider(
                                thickness = 1.dp,
                                color = GreyDark
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Text(
                                text = "App for store date with Room",
                                color = GreyDark
                            )
                        }
                    }
                }
            }
        }
    )
}