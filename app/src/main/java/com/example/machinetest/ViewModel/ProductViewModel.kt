package com.example.machinetest.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.Domain.Repository.ProductRepository
import com.example.machinetest.utility.getcurrentdateAndTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repository: ProductRepository):ViewModel() {
    val products=repository.getAllproducts()
    fun getproductbycat(cate_id: Long?)=repository.fetchproductbycat(cate_id)

    fun insertproducts(
        pname:String?,
        pdesc:String?,
        pprice:Double?,
        pcode:String?,
        cate_id:Long?,
        image: ByteArray,
    ){
        viewModelScope.launch {
           repository.insertproduct(
               pname=pname,
               pdesc = pdesc,
               pprice = pprice,
               pcode = pcode,
               cate_id = cate_id,
               image = image,
               createat = getcurrentdateAndTime().date
           )
        }
    }


}