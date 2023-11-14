package com.iessanalberto.dam2.appjuegosnavegacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.appjuegosnavegacion.screens.MainScreen
import com.iessanalberto.dam2.appjuegosnavegacion.screens.NonesScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()         // Línea para redibujar todas las funciones de la App.

    /**
     * Todas las ventanas deben recibir el navController como parámetro de entrada para poder navegar.
     */

    // Emparejar las rutas con las ventanas a las que se quiere navegar
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route
    ) {
        // Se crean tantas líneas como ventanas haya en la aplicación
        composable(route = AppScreens.MainScreen.route) { MainScreen(navController) }
        composable(route = AppScreens.NonesScreen.route) { NonesScreen(navController) }
        /**
         * TODO rutas juegos restantes
         */
    }
}