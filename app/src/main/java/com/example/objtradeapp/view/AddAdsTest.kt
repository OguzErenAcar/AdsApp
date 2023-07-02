package com.example.objtradeapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.MainActivity
import com.example.objtradeapp.ui.theme.Cl1



@Composable
fun AddAdsTest(navController: NavController,activity: Activity){

    if(ContextCompat.checkSelfPermission(LocalContext.current,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
    {
        requestPermissions(activity,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        println("burda 1")

    }else{
        val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(activity,galeriIntent,2,null)
        println("burda 2")
    }
}


@Preview(showBackground = true)
@Composable
fun show(){
}