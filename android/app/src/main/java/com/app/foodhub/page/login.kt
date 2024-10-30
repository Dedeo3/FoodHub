package com.app.foodhub.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.foodhub.R
import com.app.foodhub.component.button
import com.app.foodhub.component.inputText
import com.app.foodhub.component.passwordTextField

@Composable
fun login() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = 10.dp)
                .size(300.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Welcome to FOODHUB, in this app you can browse, \norder, and find your favorite restaurant",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Username Field
        Column(
            horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Username", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            inputText()

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Password", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            passwordTextField(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "Don't have an account?", fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Regis now", fontWeight = FontWeight.Bold, color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))
        button(action = { /*TODO*/ }, text = "Login", buttonHeight = 50.dp )
}
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        login()
    }
}

