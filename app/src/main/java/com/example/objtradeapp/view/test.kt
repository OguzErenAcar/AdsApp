package com.example.objtradeapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.objtradeapp.ui.theme.Cl4

val books = (1..10).map { "Book $it" }.toList()
val wishlisted = (1..50).map { "Wishlisted Book $it" }

@Composable
fun NestedScrollScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        // My Books section
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("My Books", style = MaterialTheme.typography.bodyMedium)
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(books) { item ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .height(120.dp)
                                .width(90.dp)
                                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(item, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }

        }
        item {
            Text("Whishlisted Books", style = MaterialTheme.typography.bodyMedium)
        }
        // Turning the list in a list of lists of two elements each
        items(wishlisted.windowed(2, 2, true)) { sublist ->
            Row(Modifier.fillMaxWidth()) {
                sublist.forEach { item ->
                    Text(
                        item, modifier = Modifier
                            .height(120.dp)
                            .padding(4.dp)
                            .background(Color.Yellow)
                            .fillParentMaxWidth(.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun test(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cl4
    ) {
         Column(verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()

        ) {

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black))
             Deneme( )

        }


    }
}
@Composable
fun Deneme() {
    Row {
        Box(
            modifier = Modifier
                .width(8.dp)
                .fillMaxHeight()
                .background(Color.Red)
        )
        Column {
            Text("Hello")
            Text("World")
        }
    }
}


@Preview
@Composable
fun View(){
    test()
}