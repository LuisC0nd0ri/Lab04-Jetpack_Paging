package com.luiscv.lab04_paging.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luiscv.lab04_paging.entities.CountryEntity
import com.luiscv.lab04_paging.model.CountryRepository
import kotlinx.coroutines.flow.Flow

class CountryViewModel : ViewModel() {
    private val countryRepository: CountryRepository = CountryRepository()

    fun items(): Flow<PagingData<CountryEntity>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 3),
            pagingSourceFactory = {CountryPagingSource(countryRepository)}

        ).flow.cachedIn(viewModelScope)

        return pager

    }

}