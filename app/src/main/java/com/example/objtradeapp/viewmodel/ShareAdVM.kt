package com.example.objtradeapp.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.Response
import com.example.objtradeapp.repository.AdsRepository
import com.example.objtradeapp.util.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ShareAdVM @Inject constructor(
    private val repository: AdsRepository
): ViewModel() {

    private val storage= FirebaseStorage.getInstance()

    var Ad= mutableStateOf<Ads?>(null)
    var Message= mutableStateOf("")



    fun shareAd(then:()->Unit){
        val uuid = UUID.randomUUID()
        val ImgName = "${uuid}.jpg"

        //yeni isim
        val ImgReferance= storage.reference.child("AdsImages").child(ImgName)
        val ImgPath= Uri.parse(Ad.value?.AdsPhotoPaths)

        //telefondaki uri ile kayıt yeni isimli img kayıt
            ImgReferance.putFile(ImgPath).addOnSuccessListener {
              val gorselReferance= FirebaseStorage.getInstance().reference.child("AdsImages").child(ImgName)
                gorselReferance.downloadUrl.addOnSuccessListener {uri->
                    viewModelScope.launch {
                        Ad.value?.AdsPhotoPaths=uri.toString()
                        val response =  repository.add(Ad.value!!)
                        when(response){
                            is Resource.Success->{
                                Message.value="Image saved"
                            }
                            is Resource.Error->{
                                Message.value=" error ${response.message}"
                            }
                            is Resource.Loading->{
                                Message.value="Image loading"
                            }
                        }
                    }.apply {
                        then()
                    }
                }

            }.addOnFailureListener{
                Exception->
                Message.value=" error ${Exception.message}"

            }




    }

}