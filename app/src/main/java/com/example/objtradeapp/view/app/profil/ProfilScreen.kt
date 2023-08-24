package com.example.objtradeapp.view.app.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.model.Profil
import com.example.objtradeapp.util.Constants.UserInfo.USERID
import com.example.objtradeapp.viewmodel.ProfilVM


@Composable
fun ProfilScreen(navController: NavController,viewmodel:ProfilVM= hiltViewModel()){
    viewmodel.getProfil(USERID)
    //composable fazladan değerlendiriliyor ve 3 kere çağırılyr
   // = ile live data gibi çalışmazken by ile çalışıyor ?
    val profil  by remember{ viewmodel.Profil}
    val error by remember{  viewmodel.message}
    val loading by remember{  viewmodel.loading}

    Surface( modifier = Modifier
        .fillMaxSize()) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {

            UserInfos(navController,profil)
            if (error.isNotEmpty())
                Text(text = error)
            if(loading)
                CircularProgressIndicator(color = Color.Red, modifier = Modifier
                    .padding()
                    .align(Alignment.CenterHorizontally))

        }
    }
}

@Composable
fun UserInfos(navController: NavController,profil:Profil?) {

    ImageRow(navController,profil)
    Column (modifier= Modifier
        .fillMaxWidth()
        .height(300.dp),
        verticalArrangement = Arrangement.Center){
         InfoRow("Gender",profil?.Gender ?: "Gizli")
        Spacer(modifier = Modifier.height(20.dp))
        InfoRow("Number",profil?.Number ?: "Gizli")
        Spacer(modifier = Modifier.height(20.dp))
        InfoRow("Age",profil?.Age.toString())
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ImageRow(navController: NavController,profil:Profil?) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(300.dp)) {
        Box (
            Modifier
                .weight(1f)
                .fillMaxSize()){
            //ımage
        }
        Box (
            Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(text = profil?.Name ?:"")
        }
    }
}

@Composable
fun InfoRow(item:String,content:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            fontSize = 20.sp,
            text = item,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
        Text(
            fontSize = 20.sp,
            text = content,
            modifier = Modifier.weight(1f) ,
            textAlign = TextAlign.End

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowProfilScreen(){
    val navController= rememberNavController()
    ProfilScreen(navController)
}