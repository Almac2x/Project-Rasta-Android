package com.rastatech.projectrasta.features.main.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.nav_graph.BottomNavGraph

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(

    mainNavController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
){

    val bottomBarNavController = rememberNavController()


    val screens = listOf(
        BottomBarScreens.Home, BottomBarScreens.MakeWish,BottomBarScreens.GemsPage,BottomBarScreens.Profile,
    )

    val showBottomBar = bottomBarNavController
        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }


    Scaffold(

        bottomBar = {

            if(showBottomBar){
                BottomBar(navController = bottomBarNavController)
            }

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())){

            BottomNavGraph(bottomBarNavController = bottomBarNavController, token = viewModel.userToken, mainNavController = mainNavController)
        }
    }

}

@Composable
fun BottomBar(navController: NavHostController){

    val screens = listOf(
        BottomBarScreens.Home, BottomBarScreens.MakeWish, BottomBarScreens.GemsPage,BottomBarScreens.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation {
        screens.forEach{screen ->

            AddItem(
                screen = screen,
                currentDestination = currentDestination ,
                navController = navController
            )

        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(BottomBarScreens.Home.route)
                launchSingleTop = true
            }
        },
    )
}