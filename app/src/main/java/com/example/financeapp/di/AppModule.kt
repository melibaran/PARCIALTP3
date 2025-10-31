package com.example.financeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.infrastructure.AppDatabase
import com.example.financeapp.infrastructure.api.ApiClientMockServer
import com.example.financeapp.data.dao.MensajeDao
import dagger.hilt.android.qualifiers.ApplicationContext // Necesario para obtener el Context


@Module
@InstallIn(SingletonComponent::class)
    class AppModule {

        //va a tener sus providers:
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "afile-db-name" // Nombre de tu base de datos
            ).build()
        }

    // 2. PROVEE EL DAO
    @Provides
    fun provideMensajeDao(database: AppDatabase): MensajeDao {
        return database.mensajeDao()
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClientMockServer()
    }

}

