package com.example.networkex.data.network

import com.example.networkex.data.model.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonResponse

    companion object {
        fun create(): PokeApiService {
            val retrofit =
                Retrofit
                    .Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PokeApiService::class.java)
        }
    }
}
