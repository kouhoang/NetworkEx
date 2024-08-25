package com.example.networkex.data.repository

import com.example.networkex.data.local.PokemonDao
import com.example.networkex.data.local.entities.PokemonEntity
import com.example.networkex.data.model.Pokemon
import com.example.networkex.data.model.PokemonResponse
import com.example.networkex.data.network.PokeApiService
import javax.inject.Inject

class PokemonRepository
    @Inject
    constructor(
        private val apiService: PokeApiService,
        private val pokemonDao: PokemonDao,
    ) {
        suspend fun getPokemons(
            limit: Int,
            offset: Int,
        ): PokemonResponse = apiService.getPokemonList(limit, offset)

        suspend fun getCachedPokemons(): List<PokemonEntity> = pokemonDao.getAllPokemons()

        suspend fun cachePokemons(pokemons: List<Pokemon>) {
            val entities = pokemons.map { PokemonEntity(it.name, it.url, it.number) }
            pokemonDao.insertAll(entities)
        }
    }
