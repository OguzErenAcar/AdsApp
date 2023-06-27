package com.example.objtradeapp.repository

import com.example.objtradeapp.model.Response
import com.example.objtradeapp.model.User
import com.example.objtradeapp.services.UsersApi
import com.example.objtradeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UsersRepository @Inject constructor(
    val api:UsersApi
) {
    suspend fun signin(User:User):Resource<Response>{
       val response=
           try {
               api.signin(User)
           }catch (e:Exception){
               return Resource.Error("signin error")
           }
        return Resource.Success(response)
    }

    suspend fun login(User:User):Resource<Response>{
        val response=
            try {
                api.login(User)
            }catch (e:Exception){
                return Resource.Error("login error")
            }
        return Resource.Success(response)
    }

    suspend fun deleteUser(User:User):Resource<Response>{
        val response=
            try {
                api.deleteUser(User)
            }catch (e:Exception){
                return Resource.Error("deleteUser error")
            }
        return Resource.Success(response)
    }
    suspend fun getUser(User:User):Resource<Response>{
        val response=
            try {
                api.getUser(User)
            }catch (e:Exception){
                return Resource.Error("getUser error")
            }
        return Resource.Success(response)
    }

    
}