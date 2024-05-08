package com.example.machinetest.Domain.Repository

import kotlinx.coroutines.flow.Flow
import productdb.CartEntity
import productdb.GetAllCartinfo
import productdb.ProductEntity

interface CartRepository {

    fun getAllcartproducts(): Flow<List<CartEntity>>
    fun getAllcartinfo():Flow<List<GetAllCartinfo>>

    fun getcartCount(): Flow<Long?>?

  suspend  fun getcartbyproductid(product_id:Long?):CartEntity?

   suspend fun getcartbycartid(cart_id:Long?):CartEntity?

    suspend fun insertcartproduct(id:Long?=null,subtotal:Double?,qty:Long?,created_at:String?,product_id: Long?)

    suspend fun updatecartproduct(id:Long?,subtotal:Double?,qty:Long?,created_at:String?,product_id: Long?)

    suspend fun deleteitem(cart_id: Long?)

    suspend fun deleteAllcartitems()



}