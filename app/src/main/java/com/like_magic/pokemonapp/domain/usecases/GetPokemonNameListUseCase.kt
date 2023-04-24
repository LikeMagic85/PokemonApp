package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonNameListUseCase @Inject constructor(private val repository: PokemonRepository) {

    operator fun invoke() = repository.getPokemonList()

}