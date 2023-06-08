package com.luiscv.lab04_paging.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luiscv.lab04_paging.entities.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)

    //@Query("SELECT * FROM countries")
    //fun pagingSource(query: String): PagingSource<Int, CountryEntity>

    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<CountryEntity>

    @Query("DELETE FROM countries")
    suspend fun clearAll()
}