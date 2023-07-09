package com.example.objtradeapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


  open class BottomBarScreen (
     val route:String,
     val title:String,
     val icon :ImageVector?,
     val hasBottomBar:Boolean,
     val hasTopBar:Boolean){

    object Home : BottomBarScreen(
        route = "ads_screen",
        title = "Home",
        icon = Icons.Default.Cottage,
        hasBottomBar = true,
        hasTopBar = true
    )
      object Message : BottomBarScreen(
          route = "message",
          title = "Message",
          icon = Icons.Default.Message,
          hasBottomBar = true,
          hasTopBar = true
      )
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person,
        hasBottomBar = true,
        hasTopBar = true
    )
    object addAds : BottomBarScreen(
        route = "addAd",
        title = "AddAd",
        icon = Icons.Default.AddBox,
        hasBottomBar = true,
        hasTopBar = true
    )
    object Notifications : BottomBarScreen(
        route = "otifications",
        title = "Notifications",
        icon = Icons.Default.Campaign,
        hasBottomBar = true,
        hasTopBar = true
    )
      object WhiteScreen : BottomBarScreen(
          route = "space",
          title = "space",
          icon = null,
          hasBottomBar = false,
          hasTopBar = false
      )
      object DetailsAd : BottomBarScreen(
          route = "DetailsAd",
          title = "DetailsAd",
          icon = null,
          hasBottomBar = false,
          hasTopBar = false
      )


}