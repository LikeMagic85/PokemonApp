package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository
import javax.inject.Inject

class LoadMorePokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(){
        repository.loadMorePokemon()
    }

}