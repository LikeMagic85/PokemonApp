package com.like_magic.pokemonapp

import android.app.Application
import com.like_magic.pokemonapp.di.DaggerAppComponent


class PokemonApp:Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

}