package com.like_magic.pokemonapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_list")
data class PokemonNameDbModel(
    @PrimaryKey
    val name: String,
    val url:String
)