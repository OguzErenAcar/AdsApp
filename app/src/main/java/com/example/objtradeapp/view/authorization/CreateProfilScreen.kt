package com.example.objtradeapp.view.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.ui.theme.ColorFirst
import com.example.objtradeapp.ui.theme.ColorFourth
import com.example.objtradeapp.ui.theme.ColorSecond


@Composable
fun CreateProfilScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorFourth),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.height(200.dp), contentAlignment = Alignment.Center) {
                Text(text = "Create Profil")

            }
            ProfilForm(navController)
            Button(onClick = {
                             navController.navigate("app")
            },Modifier.width(150.dp),colors= ButtonDefaults.buttonColors(ColorFirst) ) {
                
            }
        }
    }


}

@Composable
fun ProfilForm(navController: NavController) {
    Column(modifier= Modifier
        .width(250.dp) ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val text = remember { mutableStateOf("") }
        InputBox(text, "Email")
        Spacer(modifier = Modifier.height(20.dp))
        InputBox(text, "Email")
        Spacer(modifier = Modifier.height(20.dp))
        InputBox(text, "Email")
        Spacer(modifier = Modifier.height(20.dp))
        InputBox(text, "Email")
        Spacer(modifier = Modifier.height(20.dp))

    }

}

@Composable
fun InputBox(text: MutableState<String>, label: String) {
    Row(Modifier.fillMaxWidth()) {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            label = { Text(text = label, color = ColorSecond) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = ColorSecond,
                focusedIndicatorColor = ColorSecond,
                unfocusedIndicatorColor = ColorSecond
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            shape = RoundedCornerShape(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun showScreen() {
    CreateProfilScreen(navController = rememberNavController())
}