package com.example.objtradeapp.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.objtradeapp.view.app.notification.LineIndicator
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun pager(){

    val state = rememberPagerState { 10 }
    LineIndicator(state=state, modifier = Modifier.fillMaxWidth())

    val animationScope = rememberCoroutineScope()
    Column {
        HorizontalPager(
            modifier = Modifier.weight(0.7f),
            state = state
        ) { page ->
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = page.toString(), fontSize = 32.sp)
            }
        }

        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                animationScope.launch {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            }) {
                Text(text = "Next Page")
            }
        }
    }

}



@OptIn(ExperimentalFoundationApi::class)
@Preview( showBackground = true)
@Composable
fun Viewer(){
    pager()
}
