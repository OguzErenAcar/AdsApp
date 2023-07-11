package com.example.objtradeapp.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.objtradeapp.ui.theme.Cl1
import com.example.objtradeapp.ui.theme.ColorFirst
import com.example.objtradeapp.ui.theme.ColorSecond
import com.example.objtradeapp.util.Constants.GoogleFonts.provider

@Composable
fun LoginScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorFirst),
            verticalArrangement = Arrangement.Top
        ) {
            val imageVector = ImageVector.vectorResource(id = R.drawable.subtract)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(470.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = imageVector,
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
                        .padding(bottom = 65.dp),
                    color = ColorSecond
                )
            }
//            Image(
//                painter = painterResource(id = R.drawable.subtract ),
//                contentDescription = "image description",
//                contentScale = ContentScale.None
//            )

        }
    }
}

@Preview
@Composable
fun LoginView() {
    LoginScreen(navController = rememberNavController())
}
