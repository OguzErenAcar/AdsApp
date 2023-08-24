package com.example.objtradeapp.view.authorization


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.R
import com.example.objtradeapp.ui.theme.ColorFirst
import com.example.objtradeapp.ui.theme.ColorFourth
import com.example.objtradeapp.ui.theme.ColorSecond
import com.example.objtradeapp.util.Constants.GoogleFonts.provider

@Composable
fun LoginScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorFirst),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // val imageVector = ImageVector.vectorResource(id = R.drawable.subtract)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.subtract),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = ""
                )
                Text(
                    text = "Login",
                    fontFamily = FontFamily(
                        Font(googleFont = GoogleFont("Roboto "), fontProvider = provider)
                    ),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 55.dp),
                    color = ColorSecond
                )
            }
            LoginForm(navController)

        }
    }
}


@Composable
fun LoginForm(navController: NavController) {
    Column(
        modifier = Modifier
            .background(ColorFirst)
            .width(320.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            val text = remember { mutableStateOf("") }

            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                label = { Text(text = "Email", color = ColorSecond) },
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
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            val text = remember { mutableStateOf("") }

            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                label = { Text(text = "Password", color = ColorSecond) },
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
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
            colors = ButtonDefaults.buttonColors(ColorFourth),
            onClick = {
                navController.navigate("CreateProfil")
            }) {
            Text(
                text = "Log in",
                fontFamily = FontFamily(
                    Font(googleFont = GoogleFont("Roboto "), fontProvider = provider)
                ),
                fontSize = 15.sp,
                modifier = Modifier,
                color = ColorSecond
            )

        }
        AnotherButtons(navController)
    }
}

@Composable
fun AnotherButtons(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Or continue with",
            fontSize = 14.sp,
            color = ColorSecond
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(ColorFirst),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
                    .border(
                        width = 2.dp,
                        color = ColorFourth,
                        shape = RoundedCornerShape(size = 4.dp)
                    )


            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.google),
                    contentDescription = "",
                    Modifier.padding(end=5.dp)
                )

                Text(text = "Google", fontSize = 14.sp, color = ColorSecond)
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(ColorFirst),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
                    .border(
                        width = 2.dp,
                        color = ColorFourth,
                        shape = RoundedCornerShape(size = 4.dp)
                    )


            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.facebook),
                    contentDescription = "",
                    Modifier.padding(end=5.dp)
                )
                Text(text = "Facebook", fontSize = 14.sp, color = ColorSecond)

            }

        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Don’t have account? Create now", color = ColorSecond)
    }

}



@Preview
@Composable
fun LoginView() {
    LoginScreen(navController = rememberNavController())
}
