package com.like_magic.pokemonapp.data.network

import com.like_magic.pokemonapp.data.network.model.PokemonEntityDto
import com.like_magic.pokemonapp.data.network.model.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListDto

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int):PokemonEntityDto

    @GET
    suspend fun loadMorePokemon(@Url url:String):PokemonListDto

}