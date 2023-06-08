package com.luiscv.lab04_paging.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name_en: String,
    val name_es: String,      //mostrar
    val continent_en: String,
    val continent_es: String, //mostrar
    val capital_en: String,
    val capital_es: String,   //mostrar
    val dial_code: String,    //mostrar
    val code_2: String,       //mostrar
    val code_3: String,       //mostrar
    val tld: String,          //mostrar
    val km2: Double              //mostrar
)
