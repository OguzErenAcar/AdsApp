package com.example.objtradeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.objtradeapp.model.Profil
import com.example.objtradeapp.repository.AdsRepository
import com.example.objtradeapp.repository.ProfilsRepository
import com.example.objtradeapp.util.Constants
import com.example.objtradeapp.util.Constants.UserInfo.USERID
import com.example.objtradeapp.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class ProfilVM @Inject constructor(
    private val repository: ProfilsRepository
):ViewModel() {

    val Profil= mutableStateOf<Profil?>(null)
    val message= mutableStateOf("")




    fun getProfil(id_: Int){
        val json=JSONObject()
        val id=json.put("id",id_)
        viewModelScope.launch {
           val response= repository.getprofil(id)
            when(response){
                is Resource.Success->{
                    Profil.value=response.data
                    message.value= response.message.toString()
                }
                is Resource.Error->{
                    message.value= response.message.toString()
                }
                is Resource.Loading->{
                    message.value= response.message.toString()
                }
            }


        }

    }




}