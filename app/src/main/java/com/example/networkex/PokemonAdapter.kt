package com.example.networkex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val pokemons: MutableList<Pokemon>,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    inner class PokemonViewHolder(
        val binding: ItemPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int,
    ) {
        val pokemon = pokemons[position]
        holder.binding.apply {
            tvName.text = pokemon.name.capitalize()
            tvNumber.text = "#${pokemon.number.toString().padStart(3, '0')}"
            Picasso.get().load(pokemon.imageUrl).into(ivPokemon)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    fun addPokemons(newPokemons: List<Pokemon>) {
        val previousCount = itemCount
        pokemons.addAll(newPokemons)
        notifyItemRangeInserted(previousCount, newPokemons.size)
    }
}
