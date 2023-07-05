package com.example.objtradeapp.repository

import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.AdsList
import com.example.objtradeapp.model.Response
import com.example.objtradeapp.model.User
import com.example.objtradeapp.services.AdsApi
import com.example.objtradeapp.util.Resource
import org.json.JSONObject
import javax.inject.Inject

class AdsRepository @Inject constructor(
   private val api:AdsApi
) {
    suspend fun add(Ads: Ads): Resource<Response> {
        val response=
            try {
                api.CreateAds(Ads)
            }catch (e:Exception){
                return Resource.Error("Create Ads error ${e}")
            }
        return Resource.Success(response)
    }

    suspend fun delete(id: JSONObject): Resource<Response> {
        val response=
            try {
                api.deleteAds(id)
            }catch (e:Exception){
                return Resource.Error("delete ads error ${e.message}")
            }
        return Resource.Success(response)
    }

    suspend fun getAll(id: JSONObject): Resource<AdsList> {
        val response=
            try {
                api.getAllAds(id)
            }catch (e:Exception){
                return Resource.Error("get all ads error ${e.message}")
            }
        return Resource.Success(response)
    }
    suspend fun get(id: JSONObject): Resource<Response> {
        val response=
            try {
                api.getAds(id)
            }catch (e:Exception){
                return Resource.Error("getAds error ${e.message}")
            }
        return Resource.Success(response)
    }


}
