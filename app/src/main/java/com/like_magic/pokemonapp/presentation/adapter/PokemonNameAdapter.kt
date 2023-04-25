package com.like_magic.pokemonapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.like_magic.pokemonapp.databinding.ItemPokemonBinding
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity

class PokemonNameAdapter
    :ListAdapter<PokemonNameEntity, PokemonNameAdapter.PokemonViewHolder>(PokemonNameDiffCallback()) {

    var onItemClickListener: ((String) -> Unit)? = null

    inner class PokemonViewHolder(val binding:ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.name.text = pokemon.name.uppercase()
        holder.itemView.setOnClickListener{
            onItemClickListener?.invoke(pokemon.url)
        }
    }

}