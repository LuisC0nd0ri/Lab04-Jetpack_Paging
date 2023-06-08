package com.luiscv.lab04_paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luiscv.lab04_paging.entities.CountryEntity
import com.luiscv.lab04_paging.model.CountryRepository
import java.io.IOException
import kotlin.concurrent.thread
import kotlin.math.max


class CountryPagingSource(
    private val countryRepository: CountryRepository
) : PagingSource<Int, CountryEntity>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountryEntity> {
        return try {
            val nextPageNumber = params.key ?: 1
            val countries = countryRepository.getCountries(nextPageNumber)

            //todo: en caso la lista de countryEntity ya haya mostrado todos los datos
            //todo: retornara un valor vacio y esto llamara una exception
            if(countries.isEmpty()){
                return throw IOException()
            }
            return LoadResult.Page(
                data = countries ?: listOf(),
                prevKey = null,//if (nextPageNumber > 1) nextPageNumber - 1 else 1,
                nextKey = nextPageNumber + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CountryEntity>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

}