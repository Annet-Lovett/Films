package com.practicum.films

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val baseFilmUrl = "https://tv-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseFilmUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val filmService = retrofit.create(FilmsApi::class.java)

    private lateinit var searchButton: Button
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var filmList: RecyclerView

    private val films = ArrayList<Film>()

    private val filmAdapter = FilmAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeholderMessage = findViewById(R.id.placeholderMessage)
        searchButton = findViewById(R.id.search_button)
        queryInput = findViewById(R.id.query_input)
        filmList = findViewById(R.id.recyclerFilms)

        filmList.adapter = filmAdapter

        filmAdapter.listOfTheFilms = films

        searchButton.setOnClickListener {
            if (queryInput.text.isNotEmpty()) {
                filmService.findFilm(queryInput.text.toString()).enqueue(object :
                    Callback<FilmsResponse> {
                    override fun onResponse(
                        call: Call<FilmsResponse>,
                        response: retrofit2.Response<FilmsResponse>
                    ) {
                        if (response.code() == 200) {
                            films.clear()
                            if (response.body()?.results?.isNotEmpty() == true) {
                                films.addAll(response.body()?.results!!)
                                filmAdapter.notifyDataSetChanged()
                            }
                            if (films.isEmpty()) {
                                showMessage(getString(R.string.nothing_found), "")
                            } else {
                                showMessage("", "")
                            }
                        } else {
                            showMessage(getString(R.string.something_went_wrong), response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                        showMessage(getString(R.string.something_went_wrong), t.message.toString())
                    }

                })
            }
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            films.clear()
            filmAdapter.notifyDataSetChanged()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }

        val ringsUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX0kN4mbhX1079ru0laoMVXgulz3NynvyG7Q&s"
        val lordUri1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7W2XReHHYrnZjbfo4E8bJ-Y5M6v63YYyrbQ&s"
        val lordUri2 = "https://upload.wikimedia.org/wikipedia/ru/thumb/f/f0/The_Lord_of_the_Rings._The_Two_Towers_%E2%80%94_movie.jpg/200px-The_Lord_of_the_Rings._The_Two_Towers_%E2%80%94_movie.jpg"
        val lordUri3 = "https://cdn.ananasposter.ru/image/cache/catalog/poster/film/90/3665-1000x830.jpg"

        val ringsName = "Властелин Колец. Кольца власти"
        val lordName1 = "Властелин Колец. Братство кольца>"
        val lordName2 = "Властелин Колец. Две башни"
        val lordName3 = "Властелин Колец. Возвращение короля"

        val ringsDescription = "Действие телесериала разворачивается за тысячи лет до событий, описанных в «Хоббите» и «Властелине колец». Сюжет основан на истории Средиземья, описанных в произведениях Дж. Р. Р. Толкина. "
        val lordDescription1 = "Во Вторую эпоху Средиземья повелители эльфов, гномов и людей получают Кольца власти. Темный властелин Саурон - представитель майар втайне от них кует Единое Кольцо на Роковой горе, вкладывая в него значительную часть своей силы, чтобы подчинить себе другие Кольца и завоевать Средиземье."
        val lordDescription2 = "Пробудившись от сна, в котором Гэндальф сражался с Балрогом в Мории, Фродо Бэггинс и Сэмуайз Гэмджи оказываются затерянными в Эмин Муиле близ Мордора."
        val lordDescription3 = "Как и предыдущие фильмы трилогии, фильм был признан одним из величайших и самых влиятельных фильмов, когда-либо снятых. Фильм получил множество наград"

}