package com.like_magic.pokemonapp.di

import android.app.Application
import com.like_magic.pokemonapp.presentation.MainActivity
import com.like_magic.pokemonapp.presentation.PokemonDetailFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity:MainActivity)
    fun inject(fragment: PokemonDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ):AppComponent
    }
}