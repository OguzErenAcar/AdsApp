package com.example.objtradeapp.repository

import com.example.objtradeapp.model.Profil
import com.example.objtradeapp.model.Response
import com.example.objtradeapp.services.ProfilsApi
import com.example.objtradeapp.util.Resource
import org.json.JSONObject
import javax.inject.Inject

class ProfilsRepository @Inject constructor(
   private val api:ProfilsApi
)
{
    suspend fun createprofil(Profil: Profil): Resource<Response> {
        val response=
            try {
                api.Create(Profil)
            }catch (e:Exception){
                return Resource.Error("Create Profil error ${e}")
            }
        return Resource.Success(response)
    }

    suspend fun getprofil(id: JSONObject): Resource<Profil> {
        val response=
            try {
                api.getprofil(id)
            }catch (e:Exception){
                return Resource.Error("delete Profil error ${e.message}")
            }
        return Resource.Success(response)
    }
}
