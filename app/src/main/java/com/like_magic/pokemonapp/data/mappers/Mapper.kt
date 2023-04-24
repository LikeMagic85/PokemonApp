package com.like_magic.pokemonapp.data.mappers

import com.like_magic.pokemonapp.data.database.PokemonNameDbModel
import com.like_magic.pokemonapp.data.network.model.PokemonListDto
import com.like_magic.pokemonapp.data.network.model.PokemonNameDto
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDbModelToPokemonNameEntity(pokemonNameDbModel: PokemonNameDbModel) =
        PokemonNameEntity(pokemonNameDbModel.name, pokemonNameDbModel.url)

    fun mapPokemonListDtoToListDbModel(pokemonListDto: PokemonListDto) =
        pokemonListDto.results.map{
            mapPokemonDtoToPokemonNameDbModel(it)
        }

    private fun mapPokemonDtoToPokemonNameDbModel(pokemonNameDto: PokemonNameDto) =
        PokemonNameDbModel(pokemonNameDto.name, pokemonNameDto.url)
}