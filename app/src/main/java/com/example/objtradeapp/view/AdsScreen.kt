package com.example.objtradeapp.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.R
import com.example.objtradeapp.ui.theme.*

//https://stackoverflow.com/questions/66908737/what-is-the-equivalent-of-nestedscrollview-recyclerview-or-nested-recyclerv/66913480#66913480

@Composable
fun AdsScreen(navController: NavController, modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cl4
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {

            TopBar()
            categoryBar()
            Spacer(modifier = Modifier.height(10.dp))
             AdsView()
        }
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .background(color=Cl1,shape= RoundedCornerShape(0.8.dp))
            .fillMaxWidth()
            .height(60.dp)
        , contentAlignment = Alignment.Center

    ) {
        Text(text = "topbar")
    }
}

@Composable
fun categoryBar(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(Cl2,shape= RoundedCornerShape(1.dp))
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center

    ) {

        LazyRow(modifier=Modifier
            .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 5.dp,
                end=5.dp)
        )
        {
            items(10){
                Box(modifier=Modifier.padding(4.dp)
                    .height(80.dp)
                    .width(80.dp)
                    .background(Cl4, shape = CircleShape )
                     ){

                }
            }




        }

        //Text(text = "Category Area" )
    }
}


@Composable
fun AdsView() {

    LazyVerticalGrid(modifier=Modifier.size(1200.dp) ,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ), userScrollEnabled = false
       ) {
        items(100) {
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .size(168.dp)
            ) {
                Text(
                    text = "$it",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

    }

    }
}


@Preview(showBackground = true)
@Composable
fun ShowAdsScreen() {
    val navController = rememberNavController()
    AdsScreen(navController = navController)
}

//mevcut ekran boyutları
//val configuration = LocalConfiguration.current
//val screenHeight = configuration.screenHeightDp.dp
//val screenWidth = configuration.screenWidthDp.dp


//@Composable
//    fun AdsArea(){
//
//        Column {

//           val padingsize=10
//            Row(modifier = Modifier.padding(padingsize.dp)) {
//                repeat(2){
//                Box(modifier = Modifier
//                    .weight(1f)
//                    .size(100.dp)
//                    .background(Color.Black) ,
//                    contentAlignment = Alignment.Center
//                    ) {
//                    Text(text = "ADS")
//                }
//                    Spacer(modifier = Modifier.width((padingsize/2).dp))
//
//                }
//            }
//     }

//   }