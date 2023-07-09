package com.example.objtradeapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.ui.theme.Cl2
import com.example.objtradeapp.ui.theme.Cl4


@Composable
fun AdsDetailsScreen(navController: NavController,ID:Int?) {
    Surface(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Cl2)
        ) {

            AdsImage()
            DetailsScreen(ID)
        }

    }


}

@Composable
fun AdsImage() {
    Box(modifier= Modifier
        .fillMaxWidth()
        .background(color = Cl4)
        .height(250.dp),
        contentAlignment=Alignment.Center){
            Text("Photo",fontSize=20.sp)
    }
}


@Composable
fun DetailsScreen(ID: Int?) {
    Box(modifier= Modifier
        .fillMaxWidth()
        .height(400.dp),
        contentAlignment = Alignment.CenterStart)
    {
        Column {
        Text("ID:$ID",fontSize=30.sp)
        Text("Price",fontSize=30.sp)
        Text("Details",fontSize=30.sp)
        }


    }
}



@Preview(showBackground = true)
@Composable
fun AdsDetailsView() {
    AdsDetailsScreen(rememberNavController(),1)
}