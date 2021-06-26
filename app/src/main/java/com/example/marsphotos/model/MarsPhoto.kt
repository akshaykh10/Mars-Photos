package com.example.marsphotos.model

import com.squareup.moshi.Json

/**
 *Moshi Converter factory parses the response from the API and converts it into a list of kotlin
 * objects of type [MarsPhoto]
 */
data class MarsPhoto (
    val id:String,
    @Json(name="img_src") val imgSrcUrl:String
)