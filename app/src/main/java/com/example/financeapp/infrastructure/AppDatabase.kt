package com.example.financeapp.infrastructure

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financeapp.data.dao.MensajeDao
import com.example.financeapp.domain.model.Message

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun mensajeDao(): MensajeDao
}
