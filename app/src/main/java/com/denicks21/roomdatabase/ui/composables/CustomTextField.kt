package com.denicks21.roomdatabase.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import com.denicks21.roomdatabase.ui.theme.GreyDark
import com.denicks21.roomdatabase.ui.theme.YellowDark

@Composable
fun CustomTextField(
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    inputWrapper: String,
    labelString: String,
    readOnly: Boolean,
    maxLength: Int,
    maxLines: Int,
    onTextChanged: (String) -> Unit,
) {
    val fieldValue = remember { mutableStateOf(inputWrapper) }
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            value = fieldValue.value,
            onValueChange = {
                if (it.length <= maxLength) {
                    fieldValue.value = it
                    onTextChanged(it)
                }
            },
            modifier = modifier,
            label = {
                Text(
                    labelString,
                    color = GreyDark
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = GreyDark,
                backgroundColor = YellowDark,
                cursorColor = GreyDark,
                focusedBorderColor = GreyDark,
                unfocusedBorderColor = GreyDark,
                focusedLabelColor = GreyDark,
                placeholderColor = GreyDark
            ),
        )
    }
}