package com.luiscv.lab04_paging.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luiscv.lab04_paging.R
import com.luiscv.lab04_paging.entities.CountryEntity

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //datos por completar
    private val nameEsText = view.findViewById<TextView>(R.id.textViewNameEs) as TextView
    private val continentEsText = view.findViewById<TextView>(R.id.textViewContinentEs) as TextView
    private val capitalEsText = view.findViewById<TextView>(R.id.textViewCapitalEs) as TextView

    private val dialCodeEsText = view.findViewById<TextView>(R.id.textViewDialCode) as TextView
    private val code2EsText = view.findViewById<TextView>(R.id.textViewCode2) as TextView
    private val code3EsText = view.findViewById<TextView>(R.id.textViewCode3) as TextView
    private val tldEsText = view.findViewById<TextView>(R.id.textViewTLD) as TextView
    private val km2EsText = view.findViewById<TextView>(R.id.textViewKm2) as TextView

    fun bind(country: CountryEntity) {
        with(country) {
            nameEsText.text = name_es.toString()
            continentEsText.text = continent_es.toString()
            capitalEsText.text = capital_es.toString()

            dialCodeEsText.text = dial_code.toString()
            code2EsText.text = code_2.toString()
            code3EsText.text = code_3.toString()
            tldEsText.text = tld.toString()
            km2EsText.text = km2.toString()
        }
    }
}