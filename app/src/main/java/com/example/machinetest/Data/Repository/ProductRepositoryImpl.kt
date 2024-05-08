package com.example.machinetest.Data.Repository

import com.example.machinetest.Data.Datasource.ProductDataSource
import com.example.machinetest.Data.Datasource.UserDataSource
import com.example.machinetest.Domain.Repository.ProductRepository
import com.example.machinetest.ProductDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import productdb.ProductEntity

class ProductRepositoryImpl(database: ProductDatabase) : ProductRepository {
    val queries= ProductDataSource(database)
    override fun getAllproducts(): Flow<List<ProductEntity>> {
        return queries.getAllproducts().asFlow().mapToList()
    }

    override suspend fun insertproduct(
        id: Long?,
        pname: String?,
        pdesc: String?,
        pprice: Double?,
        pcode: String?,
        cate_id: Long?,
        image: ByteArray,
        createAt:String?

    ) {
        withContext(Dispatchers.IO){
            queries.insertproduct(
                productid = id,
                productName = pname?:"",
                prodDesc = pdesc?:"",
                prod_price = pprice?:0.0,
                prod_code = pcode?:"",
                category_id = cate_id?:0L,
                prod_image = image,
                created_at = createAt?:""

            )
        }



    }

    override fun fetchproductbycat(cate_id: Long?): Flow<List<ProductEntity>> {
        return queries.getproductbycatid(cate_id?:0L).asFlow().mapToList()
    }
}