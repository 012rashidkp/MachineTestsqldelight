package com.example.machinetest.Domain.Repository

import productdb.UserEntity

interface UserRepository {

    suspend fun insertuser(id: Long?=null,username:String,email:String,password:String)

    suspend fun fetchuser(username: String,password: String):UserEntity?
}