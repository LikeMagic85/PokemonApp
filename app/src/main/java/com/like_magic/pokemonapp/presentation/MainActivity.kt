package com.like_magic.pokemonapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.like_magic.pokemonapp.databinding.ActivityMainBinding
import com.like_magic.pokemonapp.presentation.adapter.PokemonNameAdapter


class MainActivity : ComponentActivity() {

    private lateinit var pokemonAdapter:PokemonNameAdapter
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecycler()
        val viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        viewModel.listPokemon.observe(this){
            pokemonAdapter.submitList(it)
        }
    }

    private fun initRecycler(){
        pokemonAdapter = PokemonNameAdapter()
        binding.mainRv.adapter = pokemonAdapter
    }

}

