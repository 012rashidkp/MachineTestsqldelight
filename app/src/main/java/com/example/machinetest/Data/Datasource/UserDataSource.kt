package com.example.machinetest.Data.Datasource

import com.example.machinetest.ProductDatabase
import productdb.UserEntityQueries
fun UserDataSource(database: ProductDatabase):UserEntityQueries{
    return database.userEntityQueries
}