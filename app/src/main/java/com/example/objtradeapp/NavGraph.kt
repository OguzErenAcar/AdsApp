package com.example.objtradeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.objtradeapp.util.AssetParamType
import com.example.objtradeapp.util.UribyString
import com.example.objtradeapp.view.AddAdsScreen
import com.example.objtradeapp.view.AdsDetailsScreen
import com.example.objtradeapp.view.AdsScreen
import com.example.objtradeapp.view.Compl
import com.example.objtradeapp.view.CreateProfilScreen
import com.example.objtradeapp.view.GetImageScreen
import com.example.objtradeapp.view.LoginScreen
import com.example.objtradeapp.view.ProfilScreen

@Composable
fun  NavGraph(navController: NavHostController ) {

    NavHost(
        navController=navController,
        startDestination = "app"
    )
    {
        navigation(startDestination = "Login", route = "auth"){

            composable(route="Signin"){
            }
            composable(route="Login"){
                LoginScreen(navController = navController)
            }
            composable(route="CreateProfil"){
                CreateProfilScreen(navController = navController)
            }
        }
        navigation(startDestination = BottomBarScreen.Home.route, route = "app"){

            composable(route=BottomBarScreen.Home.route){

                AdsScreen(navController = navController)
            }
            composable(route=BottomBarScreen.DetailsAd.route+"/{adID}",
                arguments = listOf(navArgument("adID"){
                    type= NavType.IntType
                })
            ){navBackStackEntry ->
                AdsDetailsScreen(navController,navBackStackEntry.arguments?.getInt("adID"))
            }
            composable(route=BottomBarScreen.Message.route){
                Compl(navController)
            }
            composable(route=BottomBarScreen.WhiteScreen.route){
                GetImageScreen(navController = navController)
            }
            composable(
                BottomBarScreen.addAds.route+"/{uri}",
                arguments = listOf(
                    navArgument("uri") {
                        type = AssetParamType()
                    }
                )
            ) {
                val uri = it.arguments?.getParcelable<UribyString>("uri")
                AddAdsScreen(navController = navController, imgUri =uri?.uri.toString()  )
            }
            composable(route=BottomBarScreen.Profile.route){

                ProfilScreen(navController = navController)
            }
            composable(route=BottomBarScreen.Notifications.route){

            }
        }


    }


}
