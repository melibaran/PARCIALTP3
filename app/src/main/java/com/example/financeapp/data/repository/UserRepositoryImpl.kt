package com.example.financeapp.data.repository

import com.example.financeapp.data.dao.UserDao
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    
    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }
    
    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
    
    override suspend fun updatePassword(email: String, newPassword: String): Int {
        return userDao.updatePassword(email, newPassword)
    }
    
    override suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }
    
    override suspend fun deleteUser(email: String): Int {
        return userDao.deleteUserByEmail(email)
    }
}

