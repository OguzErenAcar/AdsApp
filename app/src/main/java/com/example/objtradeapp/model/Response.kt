package com.example.objtradeapp.model

import org.json.JSONObject


//Api den sadece {message:"basarılı ."}
//gibi bir obje LinkedTreeMap ve içinde message değeri vardır .
// bir anahtar kilit gibi döner  .
class Response(//api den dönen obje LinkedTreeMap i Response çevirir
    val status:Boolean,
    val message:String,
    val Data:JSONObject?)