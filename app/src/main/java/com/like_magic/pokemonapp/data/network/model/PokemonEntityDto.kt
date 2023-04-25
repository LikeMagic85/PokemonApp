package com.like_magic.pokemonapp.data.network.model


import com.google.gson.annotations.SerializedName

data class PokemonEntityDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height:Int,
    @SerializedName("id")
    val id:Int,
    @SerializedName("types")
    val types: List<Type>
)