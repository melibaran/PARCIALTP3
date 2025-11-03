package com.example.financeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.domain.model.User

@Dao
interface UserDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long
    
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
    
    @Query("UPDATE user SET password = :newPassword WHERE email = :email")
    suspend fun updatePassword(email: String, newPassword: String): Int
    
    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?
    
    @Query("DELETE FROM user WHERE email = :email")
    suspend fun deleteUserByEmail(email: String): Int
}

