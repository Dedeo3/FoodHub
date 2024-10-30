package com.app.foodhub.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.app.foodhub.R

@Composable
fun inputText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var input by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = input, onValueChange = { input = it },
            placeholder = { Text(text = "Enter username") },
            leadingIcon = {Icon(painter = painterResource(id = R.drawable.person), contentDescription ="username" )},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
    }
}

@Composable
fun PasswordTextField() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.visible)
        else
            painterResource(id = R.drawable.visible_off)

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "Enter password") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_lock_24),
                    contentDescription = "Lock Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {

                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}

@Composable
@Preview
fun PasswordTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        inputText()
//        PasswordTextField()
    }
}
