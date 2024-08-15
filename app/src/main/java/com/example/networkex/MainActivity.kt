package com.example.networkex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val pokemonAdapter by lazy { PokemonAdapter(mutableListOf()) }
    private val pokeApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = pokemonAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadMorePokemon()

        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == pokemonAdapter.itemCount - 1) {
                        loadMorePokemon()
                    }
                }
            },
        )
    }

    private fun loadMorePokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = pokeApiService.getPokemonList(limit = 20, offset = pokemonAdapter.itemCount)
                withContext(Dispatchers.Main) {
                    pokemonAdapter.addPokemons(response.results)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
