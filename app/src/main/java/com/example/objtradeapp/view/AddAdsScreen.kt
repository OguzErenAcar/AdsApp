package com.example.objtradeapp.view

 import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxHeight
 import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
 import androidx.compose.foundation.rememberScrollState
 import androidx.compose.foundation.verticalScroll
 import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.mutableIntStateOf
 import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
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


    Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .height(1000.dp)
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {


                val AdItem = adsForm(navController, imgUri)
                ShareButton(navController,AdItem)
            }

    }


}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun adsForm(navController:NavController,imgUri: String): Ads {

    Box(modifier = Modifier.height(300.dp).fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        Image(painter = rememberImagePainter(imgUri ),
            contentDescription = null,
            modifier=Modifier.size(250.dp) )

    }

    val name=remember{ mutableStateOf(TextFieldValue()) }
    val price=remember{ mutableStateOf(0) }
    val description=remember{ mutableStateOf(TextFieldValue()) }
    Spacer(
        Modifier
            .height(30.dp)
            .fillMaxWidth())
    OutlinedTextField(
        value = name.value,
         onValueChange ={name.value=it},
        label={ Text(text = "name")},
        modifier = Modifier.fillMaxWidth() )

    Spacer(
        Modifier
            .height(30.dp)
            .fillMaxWidth())
     OutlinedTextField(
        value = price.value.toString(),
        onValueChange ={newvalue->
            val intvalue =newvalue.toIntOrNull()?:0
            price.value=intvalue},
        label={ Text(text = "price")},
        modifier = Modifier.fillMaxWidth() )
    Spacer(
        Modifier
            .height(30.dp)
            .fillMaxWidth())
     TextField(
        value = description.value,
        onValueChange ={description.value=it},
        label={ Text(text = "description")},
        modifier = Modifier.fillMaxWidth().height(160.dp) )
    Spacer(
        Modifier
            .height(30.dp)
            .fillMaxWidth())


    val Ad = Ads(
        AdsDescription = description.value.toString(),
        AdsID = 0,
        AdsName = name.value.toString(),
        AdsPhotoPaths = imgUri,
        AdsPrice = price.value,
        ProfilID_ = 9,
        UserID_ = 8,
        isSelled = false
    )
    return Ad

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
            Text(text = "Share",color= Color.White)
        }
    }


}

fun Share(Ad: Ads, viewModel: ShareAdVM) {
    viewModel.Ad.value=Ad
    viewModel.shareAd()
    viewModel.shareAd()

}


@Preview(showBackground = true)
@Composable
fun showAdsAdd(){
   val navController= rememberNavController()
    AddAdsScreen(navController = navController, imgUri ="" )

 }