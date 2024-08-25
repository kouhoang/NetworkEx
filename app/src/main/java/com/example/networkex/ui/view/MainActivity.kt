package com.example.networkex.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkex.R
import com.example.networkex.ui.adapter.PokemonAdapter
import com.example.networkex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private val pokemonAdapter = PokemonAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = pokemonAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

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

                    if (lastVisibleItem >= totalItemCount - 20) {
                        pokemonViewModel.loadMorePokemon()
                    }
                }
            },
        )
    }
}
