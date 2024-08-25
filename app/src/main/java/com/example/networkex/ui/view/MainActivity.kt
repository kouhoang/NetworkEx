package com.example.networkex.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkex.R
import com.example.networkex.data.network.RetrofitInstance
import com.example.networkex.data.repository.PokemonRepository
import com.example.networkex.ui.adapter.PokemonAdapter
import com.example.networkex.ui.viewmodel.PokemonViewModel
import com.example.networkex.ui.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    private val pokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(
            PokemonRepository(
                RetrofitInstance.api,
            ),
        )
    }
    private val pokemonAdapter = PokemonAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = pokemonAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // Set grid with 2 columns

        pokemonViewModel.pokemons.observe(this) { pokemons ->
            pokemonAdapter.updatePokemons(pokemons)
        }

        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    // Trigger load when there are more items left
                    if (lastVisibleItem >= totalItemCount - 20) {
                        pokemonViewModel.loadMorePokemon()
                    }
                }
            },
        )
    }
}
