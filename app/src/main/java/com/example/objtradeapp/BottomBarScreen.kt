package com.example.objtradeapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen (
    val route:String,
    val title:String,
    val icon :ImageVector){

    object Home : BottomBarScreen(
        route = "ads_screen",
        title = "Home",
        icon = Icons.Default.Cottage
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object addAds : BottomBarScreen(
        route = "addAds",
        title = "adsadd",
        icon = Icons.Default.AddBox
    )
    object Settings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )

}