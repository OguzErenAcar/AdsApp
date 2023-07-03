package com.example.objtradeapp.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.objtradeapp.MainActivity
import com.example.objtradeapp.ui.theme.Cl1






@Composable
fun AddAdstest2(){

}



@OptIn(ExperimentalCoilApi::class)
@Composable
fun AddAdsTest(navController: NavController ) {
    val context = LocalContext.current

    val launcherPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            println("ExampleScreen PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            println("ExampleScreen PERMISSION DENIED")
        }
    }


    var selectedImageURI =  remember { mutableStateOf<Uri?>(null) }
    val launcherMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageURI.value =result.data?.data
            println(selectedImageURI.value)

        } else {
            println("Secilmedi")
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(
            onClick = {
                // Check permission

                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                        val intent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        launcherMedia.launch(intent)//type mismatch required string
                    }

                    else -> {
                        // Asking for permission

                        launcherPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }
        ) {
            Text(text = "add ads image ")
        }

        Text(text =selectedImageURI.value.toString() )
        Image(
            painter = rememberImagePainter(selectedImageURI.value),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

}



@Composable
fun show(){
}