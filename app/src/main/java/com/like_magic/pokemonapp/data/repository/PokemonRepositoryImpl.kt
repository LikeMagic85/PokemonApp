package com.like_magic.pokemonapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.like_magic.pokemonapp.data.database.AppDatabase
import com.like_magic.pokemonapp.data.mappers.Mapper
import com.like_magic.pokemonapp.data.network.ApiFactory
import com.like_magic.pokemonapp.domain.PokemonRepository
import com.like_magic.pokemonapp.domain.entity.PokemonNameEntity


class PokemonRepositoryImpl(application: Application):PokemonRepository {

    private val dataBase = AppDatabase.getInstance(application).pokemonDao()
    private val apiFactory = ApiFactory.apiService
    private val mapper = Mapper()

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
}