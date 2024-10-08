package com.example.networkex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkex.data.model.Pokemon
import com.example.networkex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel
    @Inject
    constructor(
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

                    val currentList = _pokemons.value.orEmpty().toMutableList()
                    val newPokemons = response.results.filterNot { it in currentList }
                    currentList.addAll(newPokemons)
                    _pokemons.value = currentList

                    // Cache the newly loaded Pokémon
                    withContext(Dispatchers.IO) {
                        repository.cachePokemons(newPokemons)
                    }
                } catch (e: Exception) {
                    // Handle error and try loading cached data
                    val cachedPokemons =
                        withContext(Dispatchers.IO) {
                            repository.getCachedPokemons()
                        }
                    _pokemons.value = cachedPokemons.map { Pokemon(it.name, it.url) }
                } finally {
                    isLoading = false
                }
            }
        }
    }
