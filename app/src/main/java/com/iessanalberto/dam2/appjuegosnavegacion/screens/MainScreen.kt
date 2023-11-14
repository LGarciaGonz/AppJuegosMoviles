package com.iessanalberto.dam2.appjuegosnavegacion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.dam2.appjuegosnavegacion.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "APP DE JUEGOS",
                        fontWeight = FontWeight.SemiBold

                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
        // topBar {}
    ) { innerPadding ->

        var padding = innerPadding      // Variable sin uso.

        // Columna principal con dos filas de botones
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Primera fila de botones
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(route = AppScreens.NonesScreen.route) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Pares o nones")
                }

                Button(
                    onClick = { /* Hacer algo cuando se haga clic en el botón 2 */ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Piedra, papel o tijera")
                }
            }

            // Segunda fila de botones
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /* Hacer algo cuando se haga clic en el botón 3 */ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Siete y medio")
                }

                Button(
                    onClick = { /* Hacer algo cuando se haga clic en el botón 4 */ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Juego 4")
                }
            }




//            Button(
//                onClick = { navController.navigate(route = AppScreens.NonesScreen.route) },
//                modifier = Modifier.padding(innerPadding)
//            ) {
//                Text(text = "Pares o nones")
//            }
//
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "Piedra, papel o tijera")
//            }
//
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "Siete y medio")
//            }
//
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "Juego 4")
//            }

        }

    }
}