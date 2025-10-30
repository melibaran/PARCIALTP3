package com.example.financeapp.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.data.Mensaje

@Composable
fun RegisterScreen (navController1: Modifier, navController: NavHostController) {
    var lista = listOf(
        Mensaje(nombre = "Melissa", ultMensaje = "Hola!", hora = "12:00"),
        Mensaje(nombre = "Juan", ultMensaje = "Chau!", hora = "13:00"),
        Mensaje(nombre = "Ana", ultMensaje = "Nos vemos", hora = "14:00"),
    )


}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController1 = Modifier.fillMaxSize(), navController)
}