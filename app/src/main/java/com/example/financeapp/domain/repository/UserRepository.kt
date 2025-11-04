package com.example.financeapp.domain.repository

import com.example.financeapp.domain.model.User

interface UserRepository {
    
    suspend fun insertUser(user: User): Long
    
    suspend fun getUserByEmail(email: String): User?
    
    suspend fun updatePassword(email: String, newPassword: String): Int
    
    suspend fun getUserById(userId: Int): User?
    
    suspend fun deleteUser(email: String): Int
}

