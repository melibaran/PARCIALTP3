package com.example.clase5.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.clase5.data.Mensaje
import com.example.clase5.ui.theme.components.ChatComponent

@Composable
fun RegisterScreen (navController1: Modifier, navController: NavHostController) {
    //LISTAS (componentes repetidos)
    //creo una variable, que va a ser
    // una lista de objetos Mensaje
    var lista = listOf(
        Mensaje(nombre = "Melissa", ultMensaje = "Hola!", hora = "12:00"),
        Mensaje(nombre = "Juan", ultMensaje = "Chau!", hora = "13:00"),
        Mensaje(nombre = "Ana", ultMensaje = "Nos vemos", hora = "14:00"),
    )
    LazyColumn {
        //con items, puedo iterar sobre un array
        items(lista) { mensaje ->
            ChatComponent(mensaje.nombre, mensaje.ultMensaje, mensaje.hora)
        }
    }

    //los LazyColumn y LazyRow son para listas largas,
    // que se van repetir y se van cargando a medida que se scrollea
    LazyColumn {
        item {
            ChatComponent("Nombre del grupo", "ultimo mensaje", "hora")
        }
        item{
            ChatComponent("Nombre del grupo", "ultimo mensaje", "hora")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController1 = Modifier.fillMaxSize(), navController)
}