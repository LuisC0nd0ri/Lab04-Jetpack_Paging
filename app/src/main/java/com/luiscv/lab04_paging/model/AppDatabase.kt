package com.luiscv.lab04_paging.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luiscv.lab04_paging.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun operationsCountriesDao(): CountryDao
}