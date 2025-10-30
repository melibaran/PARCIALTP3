package com.example.financeapp.domain.repository

import com.example.financeapp.data.Mensaje
import com.example.financeapp.domain.model.Message

//MANEJA LAS CLASES QUE INTERACTUAN CON LOS DATOS
interface MensajeRepository {
     fun getMensajes(): List<Message>
     fun getMensajeById(id: Int): List<Message>
}