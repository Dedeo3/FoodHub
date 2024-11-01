package com.app.foodhub.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.app.foodhub.R

@Composable
fun Category(){
    val dummy = listOf(
        "https://api.duniagames.co.id/api/content/upload/file/431633521689846471.jpg",
        "https://excelso-coffee.com/wp-content/uploads/2024/05/Website_0005_Hot-Cafe-Latte-min-300x300.jpg" // hapus spasi di awal
    )

    LazyRow(modifier = Modifier.padding(top = 20.dp)) {
        items(dummy){
            CategoryItem(photoUrl = it, text = "test")
        }
    }
}

@Composable
fun CategoryItem(photoUrl:String, text:String){
    Column {
        AsyncImage(
            model = if(LocalInspectionMode.current) {
                photoUrl
            } else {
                R.drawable.person
            }, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun tes2(){
    Category()
}