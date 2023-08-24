package com.example.objtradeapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.objtradeapp.ui.theme.ObjTradeAppTheme
import com.example.objtradeapp.ui.theme.Purple40
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ObjTradeAppTheme {
                MainScreen(this)
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class,)
@Composable
fun MainScreen(activity: Activity){
    val navController= rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(

        bottomBar={ BottomBar(navController=navController )},
     //   floatingActionButton = {AddAdsbutton(navController)},
    //    floatingActionButtonPosition = FabPosition.Center,
     )
    {

        NavGraph(navController )

    }

}

@Composable
fun BottomBar(navController: NavHostController ) {
    //AddAdsbutton(navController)
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Message,
        BottomBarScreen.WhiteScreen,
        BottomBarScreen.Notifications,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //topbar bottombar diye bir kalıtım oluşturulabilr
    var screen=BottomBarScreen("","",null,false,false)
    screens.forEach { screenitem->
        if (screenitem.route==currentDestination?.route ){
            screen=screenitem
        }
    }

    AnimatedVisibility(visible = screen.hasBottomBar) {


        BottomNavigation(      modifier = Modifier.height(70.dp) // Alt menüyü dikey olarak doldur
        ) {
            screens.forEachIndexed {index, screen ->
                if(index!=2){
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController )//not work

                }
                else{
                    AddAdsbutton(navController,screen)
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
        //    Text(text = screen.title)
        },
        icon = {
            Icon(modifier = Modifier.size(30.dp)
                .wrapContentHeight(Alignment.CenterVertically),
                imageVector = screen.icon!!,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                println(screen.route)
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun AddAdsbutton(navController: NavHostController,screen: BottomBarScreen){
    Box (modifier=Modifier, Alignment.Center){
        FloatingActionButton(modifier=Modifier.padding(),
            backgroundColor = Purple40,
            onClick = {

                navController.navigate(screen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }) {
            Icon(Icons.Filled.AddCircleOutline, contentDescription = "Localized description")
        }
    }
}