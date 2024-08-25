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
            loadPokemons()
        }

        private fun loadPokemons() {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val response = repository.getPokemons(limit, offset)
                        offset += limit

                        val currentList = _pokemons.value.orEmpty().toMutableList()
                        val newPokemons = response.results.filterNot { it in currentList }
                        currentList.addAll(newPokemons)
                        _pokemons.postValue(currentList)
                    } catch (e: Exception) {
                        // Nếu có lỗi (có thể là không có mạng), thì tải từ cơ sở dữ liệu cục bộ
                        val cachedPokemons = repository.getCachedPokemons()
                        _pokemons.postValue(cachedPokemons)
                    }
                }
            }
        }

        fun loadMorePokemon() {
            if (isLoading) return

            isLoading = true
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val response = repository.getPokemons(limit, offset)
                        offset += limit

                        val currentList = _pokemons.value.orEmpty().toMutableList()
                        val newPokemons = response.results.filterNot { it in currentList }
                        currentList.addAll(newPokemons)
                        _pokemons.postValue(currentList)
                    } catch (e: Exception) {
                        // Handle error
                    } finally {
                        isLoading = false
                    }
                }
            }
        }
    }
