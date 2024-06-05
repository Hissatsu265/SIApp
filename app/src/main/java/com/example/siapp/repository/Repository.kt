package com.example.siapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.siapp.model.ApiResponse
import com.example.siapp.network.Api_service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val apiService: Api_service){

    fun searchImages(query: String): LiveData<ApiResponse?> {
        val data = MutableLiveData<ApiResponse?>()
        val requestBody = mapOf(
            "q" to query
        )
        apiService.searchImages(requestBody).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}