package com.example.objtradeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.repository.AdsRepository
import com.example.objtradeapp.repository.UsersRepository
import com.example.objtradeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AdsListVM @Inject constructor(
   private val repository: AdsRepository
):ViewModel() {

    var adsList= mutableStateOf<List<Ads>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)


    init{
        loadAds()
    }


    fun loadAds(){
        viewModelScope.launch {
            isLoading.value=true
            val result=repository.getAll(JSONObject().put("id","0"))
            when(result){
                is Resource.Success->{

                    val Ads=result.data!!.mapIndexed{index,item->
                        item
                    }
                    errorMessage.value = ""
                    isLoading.value = false
                    adsList.value += Ads
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    errorMessage.value = ""
                }

                else -> {}
            }
        }


    }




}