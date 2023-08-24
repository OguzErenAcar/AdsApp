package com.example.objtradeapp.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.webkit.PermissionRequest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.BottomBarScreen
import com.example.objtradeapp.util.UribyString
import com.google.gson.Gson
import okhttp3.internal.wait


@Composable
fun ImageScreen(navController: NavController) {

    Surface(Modifier.fillMaxSize()) {
        Column {
            //its not working after launcherMedia.launch(input) ,why?

            val sdkVersion = android.os.Build.VERSION.SDK_INT
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
                navController.popBackStack()

            }

            val launcherMedia =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                    if (uri != null) {
                        val uristring = UribyString("1", uri.toString())
                        val json = Uri.encode(Gson().toJson(uristring))
                        navController.navigate(BottomBarScreen.addAds.route + "/"+json) {
                            popUpTo(BottomBarScreen.WhiteScreen.route) {
                                inclusive = true
                            }
                        }
                    } else
                        navController.popBackStack()

                }
            LaunchedEffect(Unit) {
                val Permission = Manifest.permission.READ_EXTERNAL_STORAGE
                val input = "image/*"
                if (sdkVersion < 31) {
                    PermisionState(context, Permission) { isGranted ->
                        if (isGranted)//izin varsa
                        {
                            launcherMedia.launch(input)
                        } else//yoksa
                            launcherPermission.launch(Permission)
                    }
                } else {
                    launcherMedia.launch(input)
                }
            }

        }
    }
}


fun PermisionState(
    context: Context,
    Permision: String,
    launcher: (isGranted: Boolean) -> Unit
) {
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Permision
        ) -> {
            launcher(true)
        }

        else -> {
            launcher(false)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun show() {
    ImageScreen(navController = rememberNavController())
}


