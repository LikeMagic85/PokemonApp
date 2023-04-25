package com.like_magic.pokemonapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.like_magic.pokemonapp.domain.usecases.GetPokemonNameListUseCase
import com.like_magic.pokemonapp.domain.usecases.GetPokemonUseCase
import com.like_magic.pokemonapp.domain.usecases.LoadDataUseCase
import com.like_magic.pokemonapp.domain.usecases.LoadPokemonUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    private val loadDataUseCase:LoadDataUseCase,
    getPokemonNameList:GetPokemonNameListUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
    private val loadPokemonUseCase: LoadPokemonUseCase
): ViewModel() {

    private val scope = viewModelScope

    val listPokemon = getPokemonNameList()

    init {
        scope.launch {
            loadDataUseCase()
        }
    }

    suspend fun loadPokemon(id:Int){
        loadPokemonUseCase(id)
    }
    fun getPokemon(id:Int) = getPokemonUseCase(id)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}