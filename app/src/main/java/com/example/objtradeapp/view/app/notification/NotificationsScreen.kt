package com.example.objtradeapp.view.app.notification

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.view.pager
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize()) {
        val pages = listOf(
            Page.MyAds, Page.Favorites
        )
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tittle")
            }
            //LinearProgressIndicator(progress = 0.5f,Modifier.fillMaxWidth())
            val pagerState = rememberPagerState { pages.size }
            LineIndicator(state = pagerState, modifier = Modifier.fillMaxWidth())
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageSpacing = 0.dp,
                verticalAlignment = Alignment.CenterVertically,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                //flingBehavior = PagerDefaults.flingBehavior(state = state),
                key = null,
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    Orientation.Horizontal
                ),
                pageContent = { index ->
                    when (pages[index]) {
                        Page.MyAds -> MyAdsList(navController)
                        Page.Favorites -> MyFavoritiesList(navController)
                    }
                }
            )
        }

    }

}


sealed class Page {
    object MyAds : Page()
    object Favorites : Page()

}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

// NEW FUNCTION FOR INDICATORS
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.indicatorOffsetForPage(page: Int) =
    1f - offsetForPage(page).coerceIn(-1f, 1f).absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LineIndicator(
    modifier: Modifier = Modifier,
    state: PagerState
) {
    Row(
        modifier = modifier
            .width(32.dp * state.pageCount)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        for (i in 0 until state.pageCount) {
            val offset = state.indicatorOffsetForPage(i)
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .weight(.5f + (offset * 3f))
                    .height(8.dp)
                    .background(
                        color = Color.Red,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showNotificationScreen() {
    NotificationScreen(navController = rememberNavController())
}