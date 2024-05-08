package com.example.machinetest.Domain.Repository

import android.media.Image
import kotlinx.coroutines.flow.Flow
import productdb.ProductEntity

interface ProductRepository {
    fun getAllproducts(): Flow<List<ProductEntity>>
    suspend fun insertproduct(id:Long?=null,pname:String?,pdesc:String?,pprice:Double?,pcode:String?,cate_id:Long?,image: ByteArray,createat:String?)
    fun fetchproductbycat(cate_id: Long?):Flow<List<ProductEntity>>



}