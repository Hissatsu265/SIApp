package com.example.siapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.siapp.network.Api_service
import com.example.siapp.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val apiService = RetrofitClient.instance.create(Api_service::class.java)

        val requestBody = mapOf(
            "q" to "apple inc"
        )

        lifecycleScope.launch {
            val response = apiService.searchImages(requestBody).awaitResponse()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                apiResponse?.let {
                    println(it)
                }
            } else {
                println("Error: ${response.errorBody()?.string()}")
            }
        }
    }
}