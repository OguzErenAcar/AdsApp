package com.example.objtradeapp.services

import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.Profil
import com.example.objtradeapp.model.Response
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfilsApi {


    @POST("profil/create")
    suspend fun Create(@Body Data: Profil): Response
    @POST("/profil/getprofil")
    suspend fun getprofil(@Body Data: JSONObject): Profil

}