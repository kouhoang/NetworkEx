package com.example.networkex.data.repository

import com.example.networkex.data.local.PokemonDao
import com.example.networkex.data.local.PokemonEntity
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
        ): PokemonResponse =
            try {
                val response = apiService.getPokemonList(limit, offset)

                // Lưu dữ liệu vào Room
                val pokemonEntities =
                    response.results.map { pokemon ->
                        PokemonEntity(pokemon.number, pokemon.name, pokemon.imageUrl)
                    }
                pokemonDao.insertAll(pokemonEntities)

                response
            } catch (e: Exception) {
                // Khi có lỗi, trả về một đối tượng PokemonResponse rỗng
                PokemonResponse(emptyList())
            }

        suspend fun getCachedPokemons(): List<Pokemon> =
            pokemonDao.getAllPokemons().map { entity ->
                Pokemon(entity.name, entity.imageUrl)
            }
    }
