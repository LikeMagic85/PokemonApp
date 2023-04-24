package com.like_magic.pokemonapp.di

import android.app.Application
import com.like_magic.pokemonapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity:MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ):AppComponent
    }
}