package com.rastatech.projectrasta.features.splash.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.screens.MainScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember{
        Animatable(0f)

    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }

            )
        )
        delay(3000L)
        navController.navigate("main_screen")
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),  // inser here the logo of the app
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }

}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "splash_screen" ){
        composable(route = "splash_screen"){
            SplashScreen(navController = navController)
        }
        composable(route = "main_screen"){
            MainScreen()

        }
    }



}