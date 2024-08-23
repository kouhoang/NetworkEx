package com.example.networkex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkex.R
import com.example.networkex.data.model.Pokemon

class PokemonAdapter(
    private val pokemons: MutableList<Pokemon>,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    // Define an array of color resources
    private val colors =
        listOf(
            R.color.bg_color_1,
            R.color.bg_color_2,
            R.color.bg_color_3,
            R.color.bg_color_4,
            R.color.bg_color_5,
            R.color.bg_color_6,
            R.color.bg_color_7,
            R.color.bg_color_8,
            R.color.bg_color_9,
            R.color.bg_color_10,
        )

    inner class PokemonViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val ivPokemon: ImageView = view.findViewById(R.id.ivPokemon)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvNumber: TextView = view.findViewById(R.id.tvNumber)

        fun bind(pokemon: Pokemon) {
            // Load image using Glide
            Glide
                .with(view.context)
                .load(pokemon.imageUrl)
                .into(ivPokemon)

            // Format the Pokémon number
            val formattedNumber = String.format("#%03d", pokemon.number)
            tvNumber.text = formattedNumber

            // Capitalize the first letter of the Pokémon name
            val capitalizedName = pokemon.name.replaceFirstChar { it.uppercase() }
            tvName.text = capitalizedName

            // Set background color from colors array
            val color =
                ContextCompat.getColor(
                    view.context,
                    colors[adapterPosition % colors.size],
                )
            view.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int,
    ) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size

    fun updatePokemons(newPokemons: List<Pokemon>) {
        // Add only new Pokémon that are not already in the list
        val currentList = pokemons.toMutableList()
        newPokemons.forEach { pokemon ->
            if (pokemon !in currentList) {
                currentList.add(pokemon)
            }
        }
        // Sort the list by Pokémon number to ensure they are displayed in order
        currentList.sortBy { it.number }
        pokemons.clear()
        pokemons.addAll(currentList)
        notifyDataSetChanged()
    }
}
