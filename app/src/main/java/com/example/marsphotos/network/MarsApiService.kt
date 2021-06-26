package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL="https://android-kotlin-fun-mars-server.appspot.com"

/**
 * Build and Create an Instance of Moshi Converter Factory
 */
private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Build and create a Retrofit object
 */
private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * [MarsApiService] defines how Retrofit talks to the server using HTTP requests
 */
interface MarsApiService
{
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}

/**
 * The call to create() a function on a Retrofit object is expensive
 * And the app needs only one instance of Retrofit API service
 * use the lazy initializer to make sure it is initialised at it's first instance
 */
object MarsApi {
    val retrofitService:MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
}