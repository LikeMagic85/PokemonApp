package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository

class LoadDataUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(){
        repository.loadData()
    }
}