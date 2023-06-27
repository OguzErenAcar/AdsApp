package com.example.objtradeapp.services

import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.Response
import org.json.JSONObject
import retrofit2.http.POST

interface ProfilsApi {


    @POST("profil/create")
    suspend fun Create(Data: Ads): Response
    @POST("/profil/getprofil")
    suspend fun deleteAds(Data: JSONObject): Response



}