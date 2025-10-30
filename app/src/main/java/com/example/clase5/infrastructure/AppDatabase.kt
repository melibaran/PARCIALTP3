package com.example.clase5.infrastructure

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clase5.data.dao.MensajeDao
import com.example.clase5.domain.model.Message

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun mensajeDao(): MensajeDao
}
