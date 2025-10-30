package com.example.clase5.domain.repository

import com.example.clase5.data.Mensaje

//MANEJA LAS CLASES QUE INTERACTUAN CON LOS DATOS
interface MensajeRepository {
     fun getMensajes(): List<Mensaje>
     fun getMensajeById(id: Int): Mensaje?
}