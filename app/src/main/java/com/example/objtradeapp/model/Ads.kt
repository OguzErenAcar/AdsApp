package com.example.objtradeapp.model

data class Ads(
    val AdsDescription: String,
    val AdsID: Int,
    val AdsName: String,
    var AdsPhotoPaths: String,
    val AdsPrice: Int,
    val ProfilID_: Int,
    val UserID_: Int,
    val isSelled: Boolean,
    val CategoryID_:Int
)