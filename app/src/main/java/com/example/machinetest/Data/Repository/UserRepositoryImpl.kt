package com.example.machinetest.Data.Repository

import com.example.machinetest.Data.Datasource.UserDataSource
import com.example.machinetest.Domain.Repository.UserRepository
import com.example.machinetest.ProductDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import productdb.UserEntity


class UserRepositoryImpl(databae:ProductDatabase) : UserRepository {
    val queries= UserDataSource(databae)
    override suspend fun insertuser(
        id: Long?,
        username: String,
        email: String,
        password: String
    ) {
        withContext(Dispatchers.IO){
           queries.insertproduct(id,username,email,password)
        }
    }

    override suspend fun fetchuser(username: String, password: String): UserEntity? {
        return withContext(Dispatchers.IO){
            queries.fetchuser(username,password).executeAsOneOrNull()
        }
    }


}