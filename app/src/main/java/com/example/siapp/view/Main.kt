package com.example.siapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.siapp.R
import com.example.siapp.network.Api_service
import com.example.siapp.network.RetrofitClient
import com.example.siapp.repository.Repository
import com.example.siapp.viewmodel.MainViewModel
import com.example.siapp.viewmodel.MainViewModelFactory
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class Main : AppCompatActivity() {
    private val apiService: Api_service by lazy {
        RetrofitClient.instance.create(Api_service::class.java)
    }

    private val repository: Repository by lazy {
        Repository(apiService)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.searchResults.observe(this, Observer { response ->
            response?.let {
                println(it)
                Log.d("TAGhuuu", "onCreate: "+ it.toString())
            } ?: run {
                println("Error: No data received")
            }
        })

        viewModel.searchImages("apple inc")
    }
}