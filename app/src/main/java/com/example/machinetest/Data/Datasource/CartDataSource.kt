package com.example.machinetest.Data.Datasource

import com.example.machinetest.ProductDatabase
import productdb.CartEntityQueries
import productdb.ProductEntityQueries


fun CartDataSource(database: ProductDatabase): CartEntityQueries {
    return database.cartEntityQueries
}