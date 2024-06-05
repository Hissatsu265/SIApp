package com.example.siapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapp.model.ApiResponse
import com.example.siapp.repository.Repository

class MainViewModel (private val repository: Repository) : ViewModel() {

    private val _searchResults = MutableLiveData<ApiResponse?>()
    val searchResults: LiveData<ApiResponse?> get() = _searchResults

    fun searchImages(query: String) {
        _searchResults.value = repository.searchImages(query).value
    }
}