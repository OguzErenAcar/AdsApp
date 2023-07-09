package com.example.objtradeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.objtradeapp.BottomBarScreen
import com.example.objtradeapp.R
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.ui.theme.*
import com.example.objtradeapp.viewmodel.AdsListVM
import com.google.accompanist.flowlayout.FlowRow

//https://stackoverflow.com/questions/66908737/what-is-the-equivalent-of-nestedscrollview-recyclerview-or-nested-recyclerv/66913480#66913480

@Composable
fun AdsScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cl4
    ) {
       LazyColumn(verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally,
             modifier = Modifier
                .fillMaxSize()

        ) {
           item {
               TopBar()
           }
           item{
               categoryBar()
           }
           item{
               Spacer(modifier = Modifier.height(10.dp))
           }
           item{
               AdsList(navController)
           }

        }
    }
}

@Composable
fun AdsList(navController: NavController, viewModel: AdsListVM = hiltViewModel()) {

    val Adslist by remember { viewModel.adsList }
    val Error by remember {viewModel.errorMessage}
    val Loading by remember{viewModel.isLoading}

    //Scroll sayfanın sonuna geldiğinde yeni 10 ürün için
    //istek atıp sayfanın boyutu ayarlanabilir hem de dinamik olr 


    Box(contentAlignment = Alignment.Center,
        modifier= Modifier.fillMaxHeight()  ){
        AdsListView(Adslist, navController)
        if(Loading){
            CircularProgressIndicator(color = Color.Red,
                modifier = Modifier
                    .padding(bottom = 35.dp)
                    .align(Alignment.Center))
        }
        if(Error.isNotEmpty()){
            RetryView(error = Error) {
                viewModel.loadAds()
            }
         }

    }
}
@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun AdsListView(ads: List<Ads>, navController: NavController) {
    val size=225*ads.size/2
    LazyVerticalGrid(
        modifier = Modifier.size(size.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ), userScrollEnabled = false
    ) {
      items(ads.size){i->
          AdsBox(navController =navController , item =ads.get(i))
      }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun AdsBox(navController: NavController,item:Ads) {
    Card(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(4.dp)
            .size(168.dp),
        onClick = {
            navController.navigate(BottomBarScreen.DetailsAd.route+"/"+item.AdsID)

        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter =  rememberImagePainter(data = item.AdsPhotoPaths),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = item.AdsName,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }

}

    @Composable
    fun TopBar() {
        Box(
            modifier = Modifier
                .background(color = Cl1, shape = RoundedCornerShape(0.8.dp))
                .fillMaxWidth()
                .height(60.dp), contentAlignment = Alignment.Center

        ) {
            Text(text = "topbar")
        }
    }

    @Composable
    fun categoryBar(modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .background(Cl2, shape = RoundedCornerShape(1.dp))
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center

        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 5.dp,
                    end = 5.dp
                )
            )
            {
                items(10) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(80.dp)
                            .width(80.dp)
                            .background(Cl4, shape = CircleShape)
                    ) {

                    }
                }


            }

            //Text(text = "Category Area" )
        }
    }



@Preview(showBackground = true)
    @Composable
    fun ShowAdsScreen() {
        val navController = rememberNavController()
        AdsBox(navController = navController,Ads("asd",1,"asdad","asdad",1,1,1,false))
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