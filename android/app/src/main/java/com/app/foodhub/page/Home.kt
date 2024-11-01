package com.app.foodhub.page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.foodhub.component.Appbar
import com.app.foodhub.component.Category
import com.app.foodhub.component.Search

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home() {
    Scaffold(topBar = { Appbar() }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Search()
            Text(
                text = "Category",
                modifier = Modifier.padding(top = 20.dp, start = 15.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Category()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun tes() {
    Home()
}