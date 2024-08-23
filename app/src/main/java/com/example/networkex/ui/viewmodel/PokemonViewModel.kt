package com.example.networkex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkex.data.model.Pokemon
import com.example.networkex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(
    private val repository: PokemonRepository,
) : ViewModel() {
    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> get() = _pokemons

    private var offset = 0
    private val limit = 20
    private var isLoading = false

    init {
        loadMorePokemon()
    }

    fun loadMorePokemon() {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response =
                    withContext(Dispatchers.IO) {
                        repository.getPokemons(limit, offset)
                    }
                offset += limit

                // Get current list and add new items
                val currentList = _pokemons.value.orEmpty().toMutableList()
                val newPokemons = response.results.filterNot { it in currentList }
                currentList.addAll(newPokemons)
                _pokemons.value = currentList
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading = false
            }
        }
    }
}
