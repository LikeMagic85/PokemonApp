package com.like_magic.pokemonapp.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.like_magic.pokemonapp.data.network.ConnectivityChecker
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
    private val loadPokemonUseCase: LoadPokemonUseCase,
    application: Application
): ViewModel() {

    private val scope = viewModelScope

    private val _isOnline = MutableLiveData<Boolean>()
    val isOnline: LiveData<Boolean>
        get() = _isOnline

    val listPokemon = getPokemonNameList()

    init {
        if(ConnectivityChecker().isOnline(application)){
            scope.launch {
                loadDataUseCase()
            }
        }
        else{
            _isOnline.postValue(false)
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