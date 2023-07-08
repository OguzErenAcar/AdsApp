package com.example.objtradeapp.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.objtradeapp.BottomBarScreen
import com.example.objtradeapp.util.UribyString
import com.google.gson.Gson
import okhttp3.internal.wait


@Composable
fun GetImageScreen(navController: NavController) {


    Surface(Modifier.fillMaxSize()) {
        Column {


            val context = LocalContext.current
            val backStack= remember{mutableStateOf<Boolean>(false)}

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
                backStack.value=true

            }//bu blok asenkron çalışıyor ben bu blok bittikden sonra bazı kodlar çalıştırmak istiyorum nasıl yaparım ?

            LaunchedEffect(backStack.value) {
                if (backStack.value) {
                    navController.popBackStack()
                    backStack.value=false
                }
            }

            val selectedImageURI =  remember { mutableStateOf<Uri?>(null) }
            selectedImageURI.value=null
            val launcherMedia =rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    selectedImageURI.value =result.data?.data
                    val uristring = UribyString("1", selectedImageURI.value.toString())
                    val json = Uri.encode(Gson().toJson(uristring))
                    navController.navigate(BottomBarScreen.addAds.route+"/"+json){
                        popUpTo(BottomBarScreen.WhiteScreen.route){
                            inclusive=true
                        }
                    }
                } else {
                    println("Secilmedi")
                }
            }

            LaunchedEffect(selectedImageURI){
                if(selectedImageURI.value!=null){
                    println("burdayız")

                    selectedImageURI.value=null
                  //
                }

            }

            LaunchedEffect(Unit) {

            when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        launcherMedia.launch(intent)//type mismatch required string
                    }

                    else -> {
                        // Asking for permission
                        launcherPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

                    }
                }


            }

        }

    }
}



