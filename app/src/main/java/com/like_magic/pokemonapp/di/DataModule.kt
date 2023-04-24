package com.like_magic.pokemonapp.di

import android.app.Application
import com.like_magic.pokemonapp.data.database.AppDatabase
import com.like_magic.pokemonapp.data.database.PokemonDao
import com.like_magic.pokemonapp.data.network.ApiFactory
import com.like_magic.pokemonapp.data.network.ApiService
import com.like_magic.pokemonapp.data.repository.PokemonRepositoryImpl
import com.like_magic.pokemonapp.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl:PokemonRepositoryImpl): PokemonRepository

    companion object {

        @Provides
        fun providePokemonDao(application: Application): PokemonDao {
            return AppDatabase.getInstance(application).pokemonDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}