package com.example.objtradeapp.util

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.objtradeapp.R


sealed class Constants {

   object network {
      const val BASE_URL = "http://192.168.1.5:3000"
   }
   object UserInfo{
      const val USERID=1
   }
   object GoogleFonts{
      val provider = GoogleFont.Provider(
         providerAuthority = "com.google.android.gms.fonts",
         providerPackage = "com.google.android.gms",
         certificates = R.array.com_google_android_gms_fonts_certs)
   }
}
