package com.example.networkex.data.network

import android.content.Context
import androidx.room.Room
import com.example.networkex.data.local.PokemonDao
import com.example.networkex.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePokeApiService(retrofit: Retrofit): PokeApiService = retrofit.create(PokeApiService::class.java)

    @Provides
    @Singleton
    fun providePokemonDatabase(
        @ApplicationContext appContext: Context,
    ): PokemonDatabase =
        Room
            .databaseBuilder(appContext, PokemonDatabase::class.java, "pokemon_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providePokemonDao(database: PokemonDatabase): PokemonDao = database.pokemonDao()
}
