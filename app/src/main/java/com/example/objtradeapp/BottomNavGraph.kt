package com.example.objtradeapp

import android.app.Activity
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.objtradeapp.util.AssetParamType
import com.example.objtradeapp.util.UribyString
import com.example.objtradeapp.view.AddAdsScreen
import com.example.objtradeapp.view.AddAdsTest
import com.example.objtradeapp.view.AdsScreen

@Composable
fun BottomNavGraph(navController: NavHostController ){

    NavHost(
        navController=navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route=BottomBarScreen.Home.route){
            AdsScreen(navController = navController)
        }
        composable(route=BottomBarScreen.Profile.route){

        }
        composable(route=BottomBarScreen.addAds.route){
        AddAdsTest(navController = navController )
        }
        composable(route=BottomBarScreen.Settings.route){

        }
        composable(
            "add_ads_screen/{uri}",
            arguments = listOf(
                navArgument("uri") {
                    type = AssetParamType()
                }
            )
        ) {
            val uri = it.arguments?.getParcelable<UribyString>("uri")
            AddAdsScreen(navController = navController, imgUri =uri?.uri.toString()  )
        }

//        composable(route = "${BottomBarScreen.Home.route}/newscreen") {
//            // /home/newscreen ekranı içeriği
//        }

    }

}
