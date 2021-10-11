package com.example.bookingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import com.adrena.commerce.paging3.data.model.Movies
import com.example.bookingapp.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularViewModel(private val repository: HotelRepository) : ViewModel() {
    private val alphabetHeaders = arrayListOf<Char>()
    private val sortPositionList = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val data = arrayListOf<Movies.Movie>()

    private val _mAlphabeticOrderTabs = MutableLiveData<List<Char>>()
    val mAlphabeticOrderTabs: LiveData<List<Char>>
        get() = _mAlphabeticOrderTabs

    private val _mPopular= MutableLiveData<Movies.Movie>()
    val popular: LiveData<Movies.Movie>
        get() = _mPopular

    fun getAlphabeticOrderTabs() {
        _mAlphabeticOrderTabs.postValue(createPopularSortPositions())
    }

    val listHotel: Flow<PagingData<Movies.Movie>>
        get() = repository.getHotel()

    fun getList() {
        repository.getHotel().map { pagingData -> pagingData.insertSeparators { before, after ->
            Log.d("bbbbbb",before.toString())
            _mPopular.postValue(before)
        }}
    }

    fun getPopularSortPositions(tabPosition: Int) = sortPositionList[tabPosition]

    fun getTabPosition(listPosition: Int, isFirstPosition: Boolean = true): Int {
        return if (isFirstPosition) {
            sortPositionList.indexOf(listPosition)
        } else {
            if (sortPositionList.indexOf(listPosition + 1) > 0) {
                sortPositionList.indexOf(listPosition + 1) - 1
            } else {
                sortPositionList.indexOf(listPosition)
            }
        }
    }

    private fun createPopularSortPositions() : List<Char> {
        data.sortBy { it.title }
        data.forEachIndexed { _, knownFor ->
            alphabetHeaders.add(knownFor.title[0])
        }
        val list = alphabetHeaders.distinct().sorted()
        list.forEachIndexed { index, c ->
            sortPositionList[index] = data.indexOfFirst { it.title.startsWith(c) }
        }
        return list
    }
}