package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository
import javax.inject.Inject

class LoadPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(id:Int){
        repository.loadPokemon(id)
    }

}