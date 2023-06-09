package com.like_magic.pokemonapp.data.mappers

import com.like_magic.pokemonapp.data.database.dbmodels.PokemonDbModel
import com.like_magic.pokemonapp.data.database.dbmodels.PokemonNameDbModel
import com.like_magic.pokemonapp.data.network.model.PokemonEntityDto
import com.like_magic.pokemonapp.data.network.model.PokemonListDto
import com.like_magic.pokemonapp.data.network.model.PokemonNameDto
import com.like_magic.pokemonapp.domain.entity.PokemonEntity
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

    fun mapPokemonDbModelToPokemonEntity(pokemonDbModel: PokemonDbModel?) =
        PokemonEntity(
            pokemonDbModel?.name,
            pokemonDbModel?.type,
            pokemonDbModel?.weight,
            pokemonDbModel?.height,
            pokemonDbModel?.imgUrl
        )

    fun mapPokemonEntityDtoToPokemonDbModel(pokemonEntityDto:PokemonEntityDto) =
        PokemonDbModel(
            pokemonEntityDto.id,
            pokemonEntityDto.name,
            pokemonEntityDto.types[0].type.name,
            pokemonEntityDto.weight,
            pokemonEntityDto.height,
            "$BASE_IMG_URL${pokemonEntityDto.id}.png"
        )

    companion object {

        const val BASE_IMG_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"

    }

}