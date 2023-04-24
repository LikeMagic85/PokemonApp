package com.like_magic.pokemonapp.domain

import androidx.lifecycle.LiveData
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity

interface PokemonRepository {

    fun getPokemonList():LiveData<List<PokemonNameEntity>>

    suspend fun loadData()

}