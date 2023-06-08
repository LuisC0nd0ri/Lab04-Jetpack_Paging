package com.luiscv.lab04_paging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.luiscv.lab04_paging.R
import com.luiscv.lab04_paging.entities.CountryEntity


//class UserAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
class CountryAdapter : PagingDataAdapter<CountryEntity, CountryViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        getItem(position)?.let { holder.bind(it) }
//        val item = getItem(position)
//        holder.bind(item)

        val item = getItem(position)
        item?.let { country ->
            holder.bind(country)
        }

    }

}

class DiffUtilCallBack : DiffUtil.ItemCallback<CountryEntity>() {
    override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
        //HAY TRES CODIGOS SERIA BUENO PONER LOS TRES
        return oldItem.dial_code == newItem.dial_code && oldItem.code_2 == newItem.code_2 && oldItem.code_3 == newItem.code_3
    }

    override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
        return oldItem == newItem
    }
}