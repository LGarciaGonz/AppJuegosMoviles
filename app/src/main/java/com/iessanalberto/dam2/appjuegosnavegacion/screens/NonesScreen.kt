package com.iessanalberto.dam2.appjuegosnavegacion.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.dam2.appjuegosnavegacion.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonesScreen(navController: NavController) {

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "PARES O NONES",
                        fontWeight = FontWeight.SemiBold

                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(route = AppScreens.MainScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Flecha"
                        )
                    }
                }
            )
        }
        // topBar {}
    ) { innerPadding ->

        var puntuacion by rememberSaveable { mutableStateOf(0) }
        var paresNones by rememberSaveable { mutableStateOf("") }
        var tirada by rememberSaveable { mutableStateOf("") } //TODO pasar a int al trabajar con la variable
        val context = LocalContext.current
        var mostrarAlerta by rememberSaveable { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {

                // TEXTO NÚMERO DE ACIERTOS -------------------------------------------------
                Text(
                    text = "Puntuación: " + puntuacion,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                ) // Puntuación
            }

            Column(
                // ALINEAR EL CONTENIDO EN LA PANTALLA --------------
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // CAMPO PARES O NONES -------------------------------------------------
                OutlinedTextField(
                    value = paresNones,
                    onValueChange = { paresNones = it },
                    singleLine = true,
                    label = { Text(text = "Elige pares o nones") },
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) // Selección pares o nones


                // CAMPO TIRADA -------------------------------------------------
                OutlinedTextField(
                    value = tirada,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { tirada = it },
                    singleLine = true,
                    label = { Text(text = "Elige la tirada (0 - 3)") },
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) // Selección tirada


                // BOTÓN VALIDAR -------------------------------------------------
                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = {

                        // Controlar que los campos no estén vacíos ***********
                        if (paresNones.isNotEmpty() && tirada.isNotEmpty()) {

                            // Controlar que no se escriba una opción inválida ***********
                            if (paresNones.equals(
                                    "pares",
                                    ignoreCase = true
                                ) || paresNones.equals("nones", ignoreCase = true)
                            ) {

                                // Controlar que la tirada no sea menor que 0 ni mayor que 3 ***********
                                if (tirada.toInt() >= 0 && tirada.toInt() <= 3) {

                                    mostrarAlerta = true

                                } else {
                                    // Informar del error en la tirada
                                    Toast.makeText(
                                        context,
                                        "LA TIRADA DEBE SER DE 0 A 3",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {

                                // Informar del error en la elección
                                Toast.makeText(
                                    context,
                                    "OPCIONES VÁLIDAS: PARES - NONES",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {

                            Toast.makeText(
                                context,
                                "LOS CAMPOS NO PUEDEN ESTAR VACÍOS",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } // onClick{}

                ) {
                    Text(text = "JUGAR")
                } // Button()

                // DECLARACIÓN DE VARIABLES ********************************************
                var ganador = ""
                var mensajeGanador = ""
                var numMaquina = (0..3).random()
                var sumaNumeros = tirada.toInt() + numMaquina
                val eleccionMaquina = if (paresNones == "pares") "nones" else "pares"
                // *********************************************************************


                /**
                 * TODO LA APP SE CIERRA AL ABRIR SEGUNDA PANTALLA
                 */

                if (mostrarAlerta) {

                    ganador = tiradaMaquina(sumaNumeros, paresNones, eleccionMaquina)

                    if (ganador == paresNones) mensajeGanador = "¡Tú ganas!" else mensajeGanador = "¡Yo gano!"

                    AlertDialog(
                        title = { Text(text = "Turno de la máquina") },
                        text = { Text(text = "Yo he sacado un ${numMaquina}, suman ${sumaNumeros}.\nGanan ${ganador}.\n${mensajeGanador}") },
                        onDismissRequest = { mostrarAlerta = false },
                        confirmButton = {
                            TextButton(onClick = { mostrarAlerta = false }) {
                                Text(text = "OK")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun tiradaMaquina(sumaNumeros: Int, paresNones: String, eleccionMaquina: String): String {

    var ganador = ""

    if (sumaNumeros % 2 == 0 && paresNones.equals(
            "pares",
            ignoreCase = true
        ) || sumaNumeros % 2 != 0 && paresNones.equals("nones", ignoreCase = true)
    ) {
        ganador = paresNones
    } else {
        ganador = eleccionMaquina
    }

    return ganador
}