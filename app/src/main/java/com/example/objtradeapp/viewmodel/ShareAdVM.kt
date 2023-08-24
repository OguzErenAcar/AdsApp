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



      fun  shareAd( ) {
        val uuid = UUID.randomUUID()
        val ImgName = "${uuid}.jpg"

        //yeni isim
        val ImgReferance= storage.reference.child("AdsImages").child(ImgName)
        val ImgPath= Uri.parse(Ad.value?.AdsPhotoPaths)

        //telefondaki uri ile kayıt yeni isimli img kayıt
            ImgReferance.putFile(ImgPath).addOnSuccessListener {
                //println("uri::"+ImgReferance.downloadUrl )
                ImgReferance.downloadUrl.addOnSuccessListener {uri->
                    Ad.value?.AdsPhotoPaths=uri.toString()
                    viewModelScope.launch {
                        val response =  repository.add(Ad.value!!)//Resource<Response>
                        when(response){
                            is Resource.Success->{
                                //response.data nın tipi =>Response yani apiden gelen objedir
                                //o objede mesaj geliyorsa erişilebilinir .
                                Message.value= response.data?.message ?: "dönüş mesajı yok "
                            }
                            is Resource.Error->{
                                //response.mesage apiden gelmez repodan gelir
                                //apiden bilgi her zaman T değeri olark data olarak gelecektir
                                 Message.value="${response.message}"
                            }
                            is Resource.Loading->{
                                Message.value=response.message.toString()
                            }
                        }
                    }
                }
            }
    }

}