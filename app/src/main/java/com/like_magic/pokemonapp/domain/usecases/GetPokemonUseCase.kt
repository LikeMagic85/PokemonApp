package com.like_magic.pokemonapp.domain.usecases

import com.like_magic.pokemonapp.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor( private val repository: PokemonRepository) {

    operator fun invoke(id:Int) = repository.getPokemon(id)

}