package com.denicks21.roomdatabase.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.denicks21.roomdatabase.ui.theme.GreyDark

@Composable
fun CustomTopBar(
    title: String,
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = GreyDark,
                fontSize = 22.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onButtonClicked() }
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Drawer Menu",
                    tint = GreyDark
                )
            }
        },
    )
}