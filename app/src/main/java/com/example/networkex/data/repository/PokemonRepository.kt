package com.example.networkex.data.repository

import com.example.networkex.data.model.PokemonResponse
import com.example.networkex.data.network.PokeApiService
import javax.inject.Inject

class PokemonRepository
    @Inject
    constructor(
        private val apiService: PokeApiService,
    ) {
        suspend fun getPokemons(
            limit: Int,
            offset: Int,
        ): PokemonResponse = apiService.getPokemonList(limit, offset)
    }
