package com.example.assessmentinfinion

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assessmentinfinion.view.DetailScreen
import com.example.assessmentinfinion.view.HomeScreen
import com.example.assessmentinfinion.view.SplashScreen
import com.example.assessmentinfinion.viewmodel.DetailViewModel
import com.example.assessmentinfinion.viewmodel.WeatherViewModel

object Routes {
    const val Splash = "splash"
    const val Home = "home"
    const val Detail = "detail/{city}"
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            SplashScreen(onFinished = {
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            })
        }
        composable(Routes.Home) {
            val vm: WeatherViewModel = hiltViewModel()
            HomeScreen(vm,navController) { city -> navController.navigate("detail/$city") }
        }

            composable(Routes.Detail) { backStackEntry ->
                val city = backStackEntry.arguments?.getString("city") ?: ""
                val vm: DetailViewModel = hiltViewModel()
                DetailScreen(city = city, vm = vm, navController = navController)
            }
        }
    }

