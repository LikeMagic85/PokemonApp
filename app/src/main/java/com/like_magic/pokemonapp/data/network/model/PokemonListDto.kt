package com.like_magic.pokemonapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PokemonListDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<PokemonNameDto>
)