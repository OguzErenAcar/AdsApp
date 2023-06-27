package com.example.objtradeapp.services

import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.AdsList
import com.example.objtradeapp.model.Response
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface AdsApi {


    @POST("ads/add")
    suspend fun CreateAds(Data:Ads): Response
    @POST("ads/delete")
    suspend fun deleteAds(Data:JSONObject):Response

    @POST("ads/getall")
    suspend fun getAllAds(@Body Data:JSONObject):AdsList

    @POST("ads/get")
    suspend fun getAds(Data:JSONObject):Response
}