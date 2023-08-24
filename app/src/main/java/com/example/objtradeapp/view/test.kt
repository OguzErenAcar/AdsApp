package com.example.objtradeapp.view

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.objtradeapp.ui.theme.Cl4
import kotlin.math.absoluteValue

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun test() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cl4
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()

        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black)
            )
            Box(
                Modifier
                    .background(Color.Yellow)
                    .height(100.dp)
                    .fillMaxWidth()) {
                val result = remember { mutableStateOf<Bitmap?>(null) }
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    println(it)
                }

                Button(onClick = { launcher.launch("image/*") }) {
                    Text(text = "Take a picture")
                }

                result.value?.let { image ->
                    Image(image.asImageBitmap(), null, modifier = Modifier.fillMaxWidth())
                }

            }


        }


    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun View() {
    test()
}

@OptIn(ExperimentalFoundationApi::class)
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

