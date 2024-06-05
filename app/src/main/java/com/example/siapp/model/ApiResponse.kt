package com.example.siapp.model

data class ApiResponse(
    val searchParameters: SearchParameters,
    val images: List<Image_result>
)
