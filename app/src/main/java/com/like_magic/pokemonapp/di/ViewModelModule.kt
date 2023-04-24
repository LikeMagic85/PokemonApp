package com.like_magic.pokemonapp.di

import androidx.lifecycle.ViewModel
import com.like_magic.pokemonapp.presentation.PokemonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(PokemonViewModel::class)
    fun bindPokemonViewModel(viewModel:PokemonViewModel):ViewModel

}