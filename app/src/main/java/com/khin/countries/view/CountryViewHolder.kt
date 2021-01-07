package com.khin.countries.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khin.countries.R
import com.khin.countries.model.Country
import com.khin.countries.util.getProgressDrawable
import com.khin.countries.util.loadImage

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val countryName = itemView.findViewById<TextView>(R.id.name)
    private val countryCapital = itemView.findViewById<TextView>(R.id.capital)
    private val progressDrawable = getProgressDrawable(itemView.context)

    fun bind(country: Country) {
        countryName.text = country.countryName
        countryCapital.text = country.capital
        imageView.loadImage(country.flag, progressDrawable)
    }
}