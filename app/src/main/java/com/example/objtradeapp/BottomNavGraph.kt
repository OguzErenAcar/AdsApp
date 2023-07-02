package com.example.objtradeapp

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.objtradeapp.view.AddAdsTest
import com.example.objtradeapp.view.AdsScreen

@Composable
fun BottomNavGraph(navController: NavHostController,activity: Activity){

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
        AddAdsTest(navController = navController,activity)
        }
        composable(route=BottomBarScreen.Settings.route){

        }

    }

}