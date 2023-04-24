package com.like_magic.pokemonapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.like_magic.pokemonapp.domain.usecases.GetPokemonNameListUseCase
import com.like_magic.pokemonapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    application: Application,
    private val loadDataUseCase:LoadDataUseCase,
    getPokemonNameList:GetPokemonNameListUseCase
): AndroidViewModel(application) {

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