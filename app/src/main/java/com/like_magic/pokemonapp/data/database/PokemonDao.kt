package com.like_magic.pokemonapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_list")
    fun getPokemonList(): LiveData<List<PokemonNameDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonNameDbModel>)

    @Query("SELECT * FROM pokemon_entity_list WHERE id == :id")
    fun getPokemon(id:Int): LiveData<PokemonDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDbModel)
}
