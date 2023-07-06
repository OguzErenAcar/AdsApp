package com.example.objtradeapp.util

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


sealed class Constants {

   object network {
      const val BASE_URL = "http://192.168.1.9:3000/"
   }
   object UserInfo{
      const val USERID=18
   }
}
