package com.example.machinetest.Di

import com.example.machinetest.Data.Repository.UserRepositoryImpl
import com.example.machinetest.Domain.Repository.UserRepository
import com.example.machinetest.ProductDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserDataSource(driver: SqlDriver): UserRepository {
        return UserRepositoryImpl(ProductDatabase(driver))
    }

}