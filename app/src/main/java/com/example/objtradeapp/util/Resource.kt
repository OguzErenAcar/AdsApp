package com.example.objtradeapp.util


sealed class Resource <T>(val data:T?=null,val message:String?=null){

    //t Jenerik değişkeni için <T> ifadesi kullanmak şarttır . diğer değişkenler için şart değil
    class Success<T>(data: T):Resource<T>(data)
    class Error <T>(message: String?,data:T?=null):Resource<T>(data,message)
    class Loading <T>:Resource<T>()
}