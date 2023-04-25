package com.like_magic.pokemonapp.data.database.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_entity_list")
data class PokemonDbModel(
    @PrimaryKey
    val id: Int,
    val name:String,
    val type: String,
    val weight:Int,
    val height:Int,
    val imgUrl:String
)