package com.luiscv.lab04_paging

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luiscv.lab04_paging.adapters.CountryAdapter
import com.luiscv.lab04_paging.entities.CountryEntity
import com.luiscv.lab04_paging.model.CountriesDatabase
import com.luiscv.lab04_paging.paging.CountryViewModel
import com.luiscv.lab04_paging.ui.theme.Lab04_PagingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    companion object {
        var countriesList: List<CountryEntity> = emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //cargamos los datos
        insertarDatos()
        //cargamos la lista

        val viewModel by viewModels<CountryViewModel>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }

    }

    fun insertarDatos() {
        //aqui recogemos los datos del JSON y los insertamos a traves de room

        //Jetpack Room============================
        // Obtén el contexto de la actividad o fragmento
        val context = this
        // Crea una instancia de la base de datos usando Room.databaseBuilder
        val db = Room.databaseBuilder(
            context,
            CountriesDatabase::class.java, "countries-db"
        ).build()
        // Obtiene una instancia del DAO para realizar operaciones en la base de datos
        val dao = db.operationsCountriesDao()
        //==============================================


        //Todo: Aquí, hemos creado un objeto TypeToken que representa el tipo de la lista de
        // objetos Country. Luego, utilizamos ese TypeToken junto con fromJson para
        // deserializar directamente la lista de objetos Country del JSON.
        val jsonString = resources.openRawResource(R.raw.countries).bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val countriesListType = object : TypeToken<List<CountryEntity>>() {}.type //para que no Gson no pueda inferir en el tipo de la lista
        val countriesList = gson.fromJson<List<CountryEntity>>(jsonString, countriesListType)

        //val country = Gson().fromJson(jsonString, CountryEntity::class.java)

/*
        val countryEntities = country.map { country ->
            CountryEntity(
                name_en = country.name_en,
                name_es = country.name_es,
                continent_en = country.continent_en,
                continent_es = country.continent_es,
                capital_en = country.capital_en,
                capital_es = country.capital_es,
                dial_code = country.dial_code,
                code_2 = country.code_2,
                code_3 = country.code_3,
                tld = country.tld,
                km2 = country.km2
            )
        }*/

        //para que funcione con suspend
        runBlocking {
            withContext(Dispatchers.IO) {
                dao.insertAll(countriesList)

                MainActivity.countriesList = dao.getAllCountries()
            }
        }

    }

}