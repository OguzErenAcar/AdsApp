package com.example.objtradeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.viewmodel.ShareAdVM


@Composable
fun AddAdsScreen(navController: NavController ,imgUri: String ){


    Box(modifier = Modifier.fillMaxSize()){
        Column(

            modifier= Modifier
                .fillMaxSize()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {


            var AdItem = adsForm(navController,imgUri)
            ShareButton(navController,AdItem)
        }
    }


}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun adsForm(navController:NavController,imgUri: String): Ads {
    Image(painter = rememberImagePainter(imgUri ),
        contentDescription = null,
        modifier=Modifier.size(150.dp) )
    val name=remember{ mutableStateOf(TextFieldValue()) }
    val price=remember{ mutableStateOf(TextFieldValue()) }
    val description=remember{ mutableStateOf(TextFieldValue()) }
    Spacer(
        Modifier
            .height(20.dp)
            .fillMaxWidth())
    OutlinedTextField(value = name.value, onValueChange ={name.value=it},Modifier )
    Spacer(
        Modifier
            .height(20.dp)
            .fillMaxWidth())
    OutlinedTextField(value = price.value, onValueChange ={price.value=it} )
    Spacer(
        Modifier
            .height(20.dp)
            .fillMaxWidth())
    OutlinedTextField(value = description.value, onValueChange ={description.value=it} )
    Spacer(
        Modifier
            .height(20.dp)
            .fillMaxWidth())

    val exampleAds = Ads(
        AdsDescription = "Lorem ipsum dolor sit amet",
        AdsID = 112,
        AdsName = "Example Ad",
        AdsPhotoPaths = imgUri,
        AdsPrice = 100,
        ProfilID_ = 9,
        UserID_ = 8,
        isSelled = false
    )
    return exampleAds

}


@Composable
fun ShareButton (navController: NavController,Ad:Ads,viewModel:ShareAdVM= hiltViewModel()){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(),
        contentAlignment = Alignment.Center){
        Button(onClick = {
            Share(Ad,viewModel)
        }) {
            Text(text = "Share")
        }
    }


}

fun Share(Ad: Ads, viewModel: ShareAdVM) {
    viewModel.Ad.value=Ad
    viewModel.shareAd {
        println(viewModel.Message.value)
    }

}


//@Preview(showBackground = true)
@Composable
fun showAdsAdd(){
   val navController= rememberNavController()


 }