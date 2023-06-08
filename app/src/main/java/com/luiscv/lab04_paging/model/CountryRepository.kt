package com.luiscv.lab04_paging.model

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.luiscv.lab04_paging.MainActivity
import com.luiscv.lab04_paging.entities.CountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CountryRepository {
    fun getCountries(nextPageNumber: Int): List<CountryEntity> {

        Log.d("nextPageNumber:", nextPageNumber.toString())

        /*
        var country: CountryEntity
        val start: Int = 100 * nextPageNumber
        val end = start + 20

        for (i in start..end) {
            country = CountryEntity("A",
            "A","A",
                "A","A",
                "A",i.toString(),
                i.toString(),i.toString(),
                "A",i);

            countries.add(country)
        }*/

        return if(MainActivity.countriesList.size > nextPageNumber*20+19)
                MainActivity.countriesList.subList(nextPageNumber*20, nextPageNumber*20+19) else listOf()
    }
}

//data class ResponseUser(val users: List<User>, val nextPageNumber: Int)