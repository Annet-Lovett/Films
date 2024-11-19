package com.practicum.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(): RecyclerView.Adapter<FilmHolder>() {

    var listOfTheFilms = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfTheFilms.size
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(listOfTheFilms[position])
    }

}