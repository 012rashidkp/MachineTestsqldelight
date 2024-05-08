package com.example.machinetest.Data.Repository

import com.example.machinetest.Data.Datasource.CartDataSource
import com.example.machinetest.Data.Datasource.ProductDataSource
import com.example.machinetest.Domain.Repository.CartRepository
import com.example.machinetest.ProductDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import productdb.CartEntity
import productdb.GetAllCartinfo

class CartRepositoryImpl(database: ProductDatabase) : CartRepository {

    val queries= CartDataSource(database)

    override fun getAllcartproducts(): Flow<List<CartEntity>> {
        return queries.getAllfromcart().asFlow().mapToList()
    }

    override fun getAllcartinfo(): Flow<List<GetAllCartinfo>> {
        return queries.getAllCartinfo().asFlow().mapToList()
    }

    override fun getcartCount(): Flow<Long?>? {
        return queries.getcartcount().asFlow().mapToOneNotNull()
    }

    override suspend fun getcartbyproductid(product_id: Long?): CartEntity? {
        return withContext(Dispatchers.IO){
            queries.getitembyproductid(product_id).executeAsOneOrNull()
        }
    }

    override suspend fun getcartbycartid(cart_id: Long?): CartEntity? {
        return withContext(Dispatchers.IO){
            queries.getitembycartid(cart_id?:0L).executeAsOneOrNull()
        }
    }

    override suspend fun insertcartproduct(
        id: Long?,
        subtotal: Double?,
        qty: Long?,
        created_at: String?,
        product_id: Long?
    ) {
        withContext(Dispatchers.IO){
            queries.insertintocart(
                cartid = id,
                sub_total = subtotal?:0.0,
                qty=qty?:0L,
                created_at=created_at?:"",
                product_id = product_id

            )
        }


    }

    override suspend fun updatecartproduct(
        id: Long?,
        subtotal: Double?,
        qty: Long?,
        created_at: String?,
        product_id: Long?
    ) {
        withContext(Dispatchers.IO){
            queries.insertintocart(
                cartid = id,
                sub_total = subtotal?:0.0,
                qty=qty?:0L,
                created_at=created_at?:"",
                product_id = product_id

            )
        }
    }

    override suspend fun deleteitem(cart_id: Long?) {
        withContext(Dispatchers.IO){
            queries.deleteitem(cart_id?:0L)
        }
    }

    override suspend fun deleteAllcartitems() {
        withContext(Dispatchers.IO){
            queries.deleteAll()
        }
    }
}