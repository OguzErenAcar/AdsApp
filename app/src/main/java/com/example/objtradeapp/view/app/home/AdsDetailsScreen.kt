package com.example.objtradeapp.view.app.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.ui.theme.Cl2
import com.example.objtradeapp.ui.theme.Cl4
import com.example.objtradeapp.viewmodel.AdDetailsVM


@Composable
fun AdsDetailsScreen(navController: NavController,ID:Int?,viewmodel:AdDetailsVM= hiltViewModel()) {
    Surface(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Cl2)
        ) {
        val ads by  remember{viewmodel.ad}
        val error by remember{viewmodel.Error}
        val Loading = remember{viewmodel.Loading}

            if (ads==null){
                viewmodel.loadAd(ID!!)

            }
            println("ads: $ads")

            if (!error.isNullOrEmpty()){
                Text(text = error!!)
            }
            else if (ads!=null){//not work why ?
                DetailsScreen(ads!!)
            }
            if(Loading.value){
                //refresh
            }



        }

    }


}

@Composable
fun AdsImage(adsPhotoPaths: String) {
    Box(modifier= Modifier
        .fillMaxWidth()
        .background(color = Cl4)
        .height(250.dp),
        contentAlignment=Alignment.Center){
            Text("Photo",fontSize=20.sp)
    }
}


@Composable
fun DetailsScreen(Ad:Ads) {
    AdsImage(Ad.AdsPhotoPaths)
    Box(modifier= Modifier
        .fillMaxWidth()
        .height(400.dp),
        contentAlignment = Alignment.CenterStart)
    {
        Column {
        Text("Name : ${Ad.AdsName}",fontSize=30.sp)
        Text("Category ${Ad.CategoryID_}",fontSize=30.sp)
        Text("Price : ${Ad.AdsPrice}",fontSize=30.sp)
        Text("Description ${Ad.AdsDescription}",fontSize=30.sp)
        }


    }
}



@Preview(showBackground = true)
@Composable
fun AdsDetailsView() {
    AdsDetailsScreen(rememberNavController(),1)
}