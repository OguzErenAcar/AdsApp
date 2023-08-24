package com.example.objtradeapp.view.app.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun MyFavoritiesList(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize(), content = {
            items(10) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .height(80.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Favorite")
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        })
    }
}


@Preview(showBackground = true)
@Composable
fun showfavlistScreen() {
    MyFavoritiesList(navController = rememberNavController())
}