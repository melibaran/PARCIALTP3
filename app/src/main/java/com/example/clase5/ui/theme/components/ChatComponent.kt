package com.example.clase5.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ChatComponent(titulo: String, mensaje: String, hora: String) {
    //EJEMPLO DE LISTAS, con componentes iguales
    Row() {
        //AsyncImage es de la libreria COIL
        // para usar imagenes desde internet
        AsyncImage(
            model = "https://example.com/image.jpg",
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(20.dp))
        Row() {
            Column() {
                Text(text = titulo)
                Text(text = mensaje)
            }
            Column() {
                Text(text = hora)
                Text(text = hora)
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}