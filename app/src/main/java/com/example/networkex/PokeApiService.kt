package com.example.networkex

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonResponse
}

data class PokemonResponse(
    val results: List<Pokemon>,
)
