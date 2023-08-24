package com.example.objtradeapp.util



//Resource<Response> tipinde tanımlanan değişkenin
// . datasının tipi T den dolayı Response dur
// Response değişkenin de 3 tane değişkeni vardır status message Data
sealed class Resource <T>(val data:T?=null,val message:String?=null){

    //t Jenerik değişkeni için <T> ifadesi kullanmak şarttır . diğer değişkenler için şart değil
    class Success<T>(data: T):Resource<T>(data) //Success tanımlanan yerde data kesinlikle tanımlıdır
                                                //ve tipi de bellidir ZZ
    class Error <T>(message: String?,data:T?=null):Resource<T>(data,message)
    class Loading <T>:Resource<T>()
}