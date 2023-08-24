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
 import androidx.compose.material.Snackbar
 import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.LaunchedEffect
 import androidx.compose.runtime.MutableState
 import androidx.compose.runtime.mutableIntStateOf
 import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
 import androidx.compose.runtime.rememberCoroutineScope
 import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.Constraints
 import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.objtradeapp.model.Ads
 import com.example.objtradeapp.util.Constants
 import com.example.objtradeapp.viewmodel.ShareAdVM
 import kotlinx.coroutines.launch


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
                val message= remember{ mutableStateOf("") }

                ShareButton(navController,message,AdItem)

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.align(Alignment.TopCenter),text = message.value)
                }
            }
    }


}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun adsForm(navController:NavController,imgUri: String): Ads {

    Box(modifier = Modifier
        .height(300.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        Image(painter = rememberImagePainter(imgUri ),
            contentDescription = null,
            modifier=Modifier.size(250.dp) )

    }

    val name=remember{ mutableStateOf("") }
    val price=remember{ mutableIntStateOf(0) }
    val description=remember{ mutableStateOf("") }
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

    OutlinedTextField(
        value = description.value,
        onValueChange ={description.value=it},
        label={ Text(text = "description")},
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp) )
    Spacer(
        Modifier
            .height(30.dp)
            .fillMaxWidth())


    val Ad = Ads(
        AdsDescription =description.value.toString(),
        AdsID = 2,
        AdsName = name.value.toString(),
        AdsPhotoPaths = imgUri,
        AdsPrice = price.value,
        ProfilID_ = Constants.UserInfo.USERID,
        UserID_ = Constants.UserInfo.USERID,
        isSelled = false,
        CategoryID_ = 1
    )
    return Ad

}


@Composable
fun ShareButton (navController: NavController, message: MutableState<String>, Ad:Ads, viewModel:ShareAdVM= hiltViewModel()){

    val scope = rememberCoroutineScope() // CoroutineScope for managing coroutines


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(),
        contentAlignment = Alignment.Center){
        Button(onClick = {
                viewModel.Ad.value=Ad
                viewModel.shareAd()


        }) {
            Text(text = "Share",color= Color.White)
        }
    }
    LaunchedEffect(viewModel.Message.value) {
        if(viewModel.Message.value!=""){
            message.value=viewModel.Message.value
            //SnackbarMessage(message.value) ??
            navController.navigateUp()
        }
    }
}


@Composable
fun SnackbarMessage(Message:String){

    Snackbar  {
        Text(Message)
    }

}



@Preview(showBackground = true)
@Composable
fun showAdsAdd(){
   val navController= rememberNavController()
    AddAdsScreen(navController = navController, imgUri ="" )

 }