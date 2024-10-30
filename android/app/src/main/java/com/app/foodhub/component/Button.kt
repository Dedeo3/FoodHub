package com.app.foodhub.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import com.app.foodhub.R


@Composable
fun button(action: @Composable ()->Unit, text: String, buttonWidth: Dp, buttonHeight: Dp) {
    Button(
        onClick = { action },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary)),
        shape = ButtonDefaults.shape,
        modifier = Modifier.size(buttonWidth, buttonHeight) // Mengatur ukuran Button
    ) {
        Text(text = text, color = colorResource(id = R.color.white))
    }
}