package com.like_magic.pokemonapp.data.network.model


import com.google.gson.annotations.SerializedName

data class PokemonNameDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)