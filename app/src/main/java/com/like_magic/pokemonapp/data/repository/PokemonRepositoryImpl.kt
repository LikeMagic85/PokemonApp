package com.like_magic.pokemonapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.like_magic.pokemonapp.data.database.PokemonDao
import com.like_magic.pokemonapp.data.mappers.Mapper
import com.like_magic.pokemonapp.data.network.ApiService
import com.like_magic.pokemonapp.domain.PokemonRepository
import com.like_magic.pokemonapp.domain.entity.PokemonEntity
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val dataBase:PokemonDao,
    private val mapper:Mapper,
    private val apiFactory:ApiService
):PokemonRepository {

    override suspend fun loadData() {
        dataBase.insertPokemonList(
            mapper.mapPokemonListDtoToListDbModel(apiFactory.getPokemonList())
        )
    }

    override fun getPokemonList(): LiveData<List<PokemonNameEntity>> {
        return dataBase.getPokemonList().map { list ->
            list.map {
                mapper.mapDbModelToPokemonNameEntity(it)
            }
        }
    }

    override fun getPokemon(id:Int): LiveData<PokemonEntity?> {
        return dataBase.getPokemon(id).map {
            mapper.mapPokemonDbModelToPokemonEntity(it)
        }
    }

    override suspend fun loadPokemon(id:Int){
        dataBase.insertPokemon(
            mapper.mapPokemonEntityDtoToPokemonDbModel(
                apiFactory.getPokemon(id)
            )
        )
    }

    private suspend fun getLinkToNextPage():String =
        apiFactory.getPokemonList().next

    override suspend fun loadMorePokemon() {
        dataBase.insertPokemonList(
            mapper.mapPokemonListDtoToListDbModel(
                apiFactory.loadMorePokemon(getLinkToNextPage())
            )
        )
    }
}