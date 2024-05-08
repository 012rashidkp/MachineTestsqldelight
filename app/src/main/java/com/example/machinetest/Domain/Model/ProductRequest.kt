package com.example.machinetest.Domain.Model

data class ProductRequest(
    val productName:String?,
    val productdesc:String?,
    val productprice:Double?,
    val productcode:String?,
    val category_id:Int?,
    val product_image:ByteArray
)
