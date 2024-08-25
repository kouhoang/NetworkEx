package com.example.networkex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.networkex.data.local.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
