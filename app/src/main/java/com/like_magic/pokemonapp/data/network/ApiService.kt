package com.like_magic.pokemonapp.data.network

import com.like_magic.pokemonapp.data.network.model.PokemonListDto
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListDto

}