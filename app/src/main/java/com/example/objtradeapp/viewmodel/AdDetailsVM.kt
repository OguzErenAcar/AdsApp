package com.example.objtradeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ListenableWorker.Result.Success
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.repository.AdsRepository
import com.example.objtradeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdDetailsVM @Inject constructor(
   private val repository:AdsRepository
):ViewModel() {
    val ad= mutableStateOf<Ads?>(null)
    val Error= mutableStateOf<String?>(null)
    val Loading = mutableStateOf<Boolean>(false)
    fun loadAd(id:Int) {
        viewModelScope.launch {
            val result=repository.get(mutableMapOf("id" to id))

            when(result){
              is  Resource.Success -> {
                  ad.value=result.data
                  Loading.value=false
                }
              is  Resource.Error -> {
                  Error.value=result.message
                  Loading.value=false

              }

              is  Resource.Loading -> {
                  Loading.value=true
              }
            }
        }
   }


}