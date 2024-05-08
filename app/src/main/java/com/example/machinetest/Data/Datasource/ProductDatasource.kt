package com.example.machinetest.Data.Datasource

import com.example.machinetest.ProductDatabase
import productdb.ProductEntity
import productdb.ProductEntityQueries
import productdb.UserEntityQueries

fun ProductDataSource(database: ProductDatabase): ProductEntityQueries {
    return database.productEntityQueries
}