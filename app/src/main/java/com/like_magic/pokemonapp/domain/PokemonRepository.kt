package com.like_magic.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.like_magic.pokemonapp.domain.entity.PokemonEntity
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity

interface PokemonRepository {

    fun getPokemonList():LiveData<List<PokemonNameEntity>>

    suspend fun loadData()

    fun getPokemon(id:Int):LiveData<PokemonEntity>

    suspend fun loadPokemon(id:Int)

}