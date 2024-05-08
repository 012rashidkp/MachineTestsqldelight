package com.example.machinetest.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.Domain.Repository.CartRepository
import com.example.machinetest.utility.getcurrentdateAndTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor (val repository: CartRepository):ViewModel() {
    val cartproducts=repository.getAllcartproducts()
    val getcartcount=repository.getcartCount()
    val cartAllinfo=repository.getAllcartinfo()

    suspend fun checkproductexist(product_id: Long?)=repository.getcartbyproductid(product_id)

    fun insertcartproducts(
        subtotal:Double?,
        product_id: Long?
    ){
        viewModelScope.launch {
            repository.insertcartproduct(
                subtotal=subtotal,
                qty = 1,
                product_id = product_id,
                created_at = getcurrentdateAndTime().date
            )



        }

    }

    fun updatecart(
        cart_id:Long?,
        subtotal:Double?,
        product_id: Long?,
        qty:Long?

    ){
      viewModelScope.launch {
          repository.updatecartproduct(
              id = cart_id,
              subtotal = subtotal,
              product_id = product_id,
              qty = qty,
              created_at = getcurrentdateAndTime().date
          )
      }
    }

    fun deleteitem(id:Long?){
        viewModelScope.launch {
            repository.deleteitem(id)
        }
    }

    fun deleteAllfromcart(){
        viewModelScope.launch {
            repository.deleteAllcartitems()
        }
    }






}