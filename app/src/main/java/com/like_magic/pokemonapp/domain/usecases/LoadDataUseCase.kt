package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(){
        repository.loadData()
    }
}