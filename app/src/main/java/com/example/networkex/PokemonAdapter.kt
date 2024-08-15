package com.example.networkex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.networkex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val pokemons: MutableList<Pokemon>,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private val backgroundColors =
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
            tvName.text = pokemon.name.replaceFirstChar { it.uppercase() }
            tvNumber.text = "#${pokemon.number.toString().padStart(3, '0')}"
            Picasso.get().load(pokemon.imageUrl).into(ivPokemon)

            // Apply background color to the CardView directly
            val colorResId = backgroundColors[position % backgroundColors.size]
            val color = ContextCompat.getColor(cardView.context, colorResId)
            cardView.setCardBackgroundColor(color)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    fun addPokemons(newPokemons: List<Pokemon>) {
        val previousCount = itemCount
        pokemons.addAll(newPokemons)
        notifyItemRangeInserted(previousCount, newPokemons.size)
    }
}
