package com.like_magic.pokemonapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity

class PokemonNameDiffCallback:DiffUtil.ItemCallback<PokemonNameEntity>() {

    override fun areItemsTheSame(oldItem: PokemonNameEntity, newItem: PokemonNameEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: PokemonNameEntity,
        newItem: PokemonNameEntity
    ): Boolean {
        return oldItem == newItem
    }


}