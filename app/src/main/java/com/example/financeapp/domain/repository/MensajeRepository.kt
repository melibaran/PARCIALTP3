package com.example.financeapp.domain.repository

import com.example.financeapp.data.Mensaje

//MANEJA LAS CLASES QUE INTERACTUAN CON LOS DATOS
interface MensajeRepository {
     fun getMensajes(): List<Mensaje>
     fun getMensajeById(id: Int): Mensaje?
}