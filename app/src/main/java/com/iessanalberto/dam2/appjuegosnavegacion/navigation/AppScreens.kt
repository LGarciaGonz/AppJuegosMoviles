package com.iessanalberto.dam2.appjuegosnavegacion.navigation

sealed class AppScreens(val route : String) {

    // Definir y limitar las rutas de la navegación
    object MainScreen : AppScreens(route = "main_screen")
    object NonesScreen : AppScreens(route = "nones_screen")

}