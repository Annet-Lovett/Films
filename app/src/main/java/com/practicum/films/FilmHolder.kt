package com.practicum.films

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FilmHolder(item: View): RecyclerView.ViewHolder(item) {

    private val filmImg = item.findViewById<ImageView>(R.id.filmImg)
    private val nameOfTheFilm = item.findViewById<TextView>(R.id.nameOfTheFilm)
    private val descriptionOfTheFilm = item.findViewById<TextView>(R.id.descriptionOfTheFilm)

    fun bind (film: Film) {
        val imgUrl = film.image
        val filmName = film.title
        val filmDescriptoin = film.description

        Glide.with(itemView)
            .load(imgUrl)
            .into(filmImg)

        nameOfTheFilm.text = filmName
        descriptionOfTheFilm.text = filmDescriptoin
    }

}