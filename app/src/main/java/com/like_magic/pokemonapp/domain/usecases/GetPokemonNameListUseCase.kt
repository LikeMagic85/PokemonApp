package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository

class GetPokemonNameListUseCase(private val repository: PokemonRepository) {

    operator fun invoke() = repository.getPokemonList()

}