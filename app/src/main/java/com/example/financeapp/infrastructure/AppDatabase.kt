package com.example.financeapp.infrastructure

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.financeapp.data.dao.UserDao

import com.example.financeapp.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao
}
