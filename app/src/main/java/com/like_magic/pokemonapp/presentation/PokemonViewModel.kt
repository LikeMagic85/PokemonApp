package com.like_magic.pokemonapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.like_magic.pokemonapp.data.repository.PokemonRepositoryImpl
import com.like_magic.pokemonapp.domain.usecases.GetPokemonNameListUseCase
import com.like_magic.pokemonapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application): AndroidViewModel(application) {
    private val repository = PokemonRepositoryImpl(application)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getPokemonNameList = GetPokemonNameListUseCase(repository)
    private val scope = viewModelScope

    val listPokemon = getPokemonNameList()

    init {
        scope.launch {
            loadDataUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}