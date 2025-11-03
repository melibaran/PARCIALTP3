package com.example.financeapp.di

import android.content.Context
import androidx.room.Room
import com.example.financeapp.data.dao.UserDao
import com.example.financeapp.data.repository.UserRepositoryImpl
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.domain.repository.UserRepository
import com.example.financeapp.infrastructure.AppDatabase
import com.example.financeapp.infrastructure.api.ApiClientMockServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
    class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "finance-app-db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClientMockServer()
    }

}

