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
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.objtradeapp.BottomBarScreen
import com.example.objtradeapp.util.UribyString
import com.google.gson.Gson
import okhttp3.internal.wait


@Composable
fun GetImageScreen(navController: NavController) {
    /*PSUDE code
    get sdk versiyon
    if 29 ve altıysa
       -- mevcut kod --
       RequestPermisiom
    SelectImage//mesala navBack burda Unit parametre olabilir
    navbackStack


    RequestPermision



    * */
    Surface(Modifier.fillMaxSize()) {
        Column {

            val sdkVersion = android.os.Build.VERSION.SDK_INT
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
                    navController.popBackStack()
                    backStack.value=false
            }
            //class yazmak daha mantıklı
              val launcherMedia= selectImage(navController = navController)

            LaunchedEffect(Unit) {
                val Permission=Manifest.permission.READ_EXTERNAL_STORAGE
                val intent =Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                if (sdkVersion<29) {
                   PermisionState(context,Permission) { isGranted ->
                       if (isGranted)//izin varsa
                       {
                       launcherMedia.launch(intent)
                   }else//yoksa
                           launcherPermission.launch(Permission)
                   }
               }else{
                    launcherMedia.launch(intent)
               }
            }

        }

    }
}

//asıl soru şu neden parametre fonksiyon olrak verilir anladık kod bloğunun içine kod bloğu yazmak için
//peki neden parametre fonksiyon parametre alır ???
//bir fonksiyonun parametresinin almasının sebebi  o fonksiyounun kendi kod bloğunun tanımlanan
//yerinde parametreyi kullanması içindir
fun PermisionState(context:Context,Permision:String,launcher:(isGranted:Boolean)->Unit){//parametreler fonksiyonda ilgili yerde kullanılmak içindir
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Permision
        ) -> {
            launcher(true)//burda ilgili yerde kullanılmış ama 1 sn kullanılmadan önce bir blok daha var
                                    //burda al bu parametreyi kullanıp veya kullanmayıp beni derleyip çağır diyor
                                    //yani ilk çağırılan yere parametre yolluyor
        }
        else -> {
            // Asking for permission
            launcher(false)
        }
    }
}
@Composable
fun selectImage(navController:NavController ):ManagedActivityResultLauncher<Intent, ActivityResult> {

    val selectedImageURI =  remember { mutableStateOf<Uri?>(null) }
    selectedImageURI.value=null
    val launcherMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageURI.value = result.data?.data
                val uristring = UribyString("1", selectedImageURI.value.toString())
                val json = Uri.encode(Gson().toJson(uristring))
                navController.navigate(BottomBarScreen.addAds.route + "/" + json) {
                    popUpTo(BottomBarScreen.WhiteScreen.route) {
                        inclusive = true
                    }
                }
            } else {
                println("Secilmedi")
            }
        }
    return launcherMedia
}

