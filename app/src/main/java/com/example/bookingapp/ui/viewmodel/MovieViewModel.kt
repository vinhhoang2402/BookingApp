package com.example.bookingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.common.Resource.Resource
import com.example.bookingapp.repository.MovieRepository
import com.example.bookingapp.ui.model.MoviesResponse
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _mPopular= MutableLiveData<Resource<MoviesResponse>>()
    val popular: LiveData<Resource<MoviesResponse>>
        get() = _mPopular

    private val _mRate= MutableLiveData<Resource<MoviesResponse>>()
    val rate: LiveData<Resource<MoviesResponse>>
        get() = _mRate

    private val _mTrend= MutableLiveData<Resource<MoviesResponse>>()
    val trend: LiveData<Resource<MoviesResponse>>
        get() = _mTrend

    init {
        getPopular()
        getRate()
        getTrend()
    }

    private fun getTrend() {
        viewModelScope.launch {
            repository.getTrendRepo().let {
                _mTrend.postValue(Resource.success(it))
            }
        }
    }

    private fun getRate() {
        viewModelScope.launch {
            repository.getRateRepo().let {
                _mRate.postValue(Resource.success(it))
            }
        }
    }

    private fun getPopular() {
        viewModelScope.launch {
            repository.getPopularRepo().let {
                _mPopular.postValue(Resource.success(it))
            }
        }
    }

}