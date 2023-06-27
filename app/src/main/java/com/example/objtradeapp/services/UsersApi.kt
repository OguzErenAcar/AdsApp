package com.example.objtradeapp.services

import com.example.objtradeapp.model.Ads
import com.example.objtradeapp.model.Response
import com.example.objtradeapp.model.User
import org.json.JSONObject
import retrofit2.http.POST

interface UsersApi {

    @POST("account/signin")
    suspend fun signin(Data: User): Response
    @POST("account/login")
    suspend fun login(Data: User): Response

    @POST("account/deleteAccount")
    suspend fun deleteUser(Data: User): Response

    @POST("account/getAccount")
    suspend fun getUser(Data: User): Response


}