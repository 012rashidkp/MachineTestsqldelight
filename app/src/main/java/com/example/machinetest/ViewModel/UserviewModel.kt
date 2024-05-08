package com.example.machinetest.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.Domain.Repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserviewModel @Inject constructor ( val repository: UserRepository):ViewModel() {

    fun insertusers(username:String,email:String,password:String){
        viewModelScope.launch {
            repository.insertuser(id = null, username,email,password)
        }
    }

    suspend fun getuser(username: String,password: String)  =repository.fetchuser(username,password)



}