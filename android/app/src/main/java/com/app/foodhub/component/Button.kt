package com.app.foodhub.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.foodhub.R


@Composable
fun ButtonCustom(action: @Composable ()->Unit, text: String, buttonHeight: Dp) {
    Button(
        onClick = { action },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(buttonHeight).fillMaxWidth() // Mengatur ukuran Button
    ) {
        Text(text = text.uppercase(), color = colorResource(id = R.color.white))
    }
}