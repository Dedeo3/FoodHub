package com.app.foodhub.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.foodhub.R

@Composable
fun appbar(){
    Row(
        Modifier
            .background(colorResource(id = R.color.primary))
            .padding(15.dp)
            .fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Welcome back, \n Ryoo", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = colorResource(
                id = R.color.white
            ) )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Ovo: Rp ,", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = colorResource(
                id = R.color.white
            ) )
        }
        Box(modifier = Modifier.fillMaxWidth().height(75.dp), contentAlignment = Alignment.CenterEnd) {
            Image(painter = painterResource(id = R.drawable.baseline_shopping_cart_24), contentDescription = "cart", modifier = Modifier.size(30.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Surface(
//        modifier = Modifier.fillMaxSize()
    ) {
        appbar()
    }
}