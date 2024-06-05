package com.example.siapp.network

import retrofit2.Call
import com.example.siapp.model.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api_service {
    @Headers("X-API-KEY: 889c5acc82d4ae66bfffbe5ac852d708c493e3e9", "Content-Type: application/json")
    @POST("search")
    fun searchImages(@Body request: Map<String, String>): Call<ApiResponse>

}


