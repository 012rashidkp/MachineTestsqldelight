package com.example.machinetest.Di

import com.example.machinetest.Data.Repository.ProductRepositoryImpl
import com.example.machinetest.Data.Repository.UserRepositoryImpl
import com.example.machinetest.Domain.Repository.ProductRepository
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
object ProductModule {
    @Provides
    @Singleton
    fun provideProductDataSource(driver: SqlDriver): ProductRepository {
        return ProductRepositoryImpl(ProductDatabase(driver))
    }

}