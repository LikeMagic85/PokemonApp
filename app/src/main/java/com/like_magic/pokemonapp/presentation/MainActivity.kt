package com.like_magic.pokemonapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.like_magic.pokemonapp.PokemonApp
import com.like_magic.pokemonapp.R
import com.like_magic.pokemonapp.databinding.ActivityMainBinding
import com.like_magic.pokemonapp.presentation.adapter.PokemonNameAdapter
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var pokemonAdapter:PokemonNameAdapter
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as PokemonApp).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(binding.root)
        initRecycler()
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory)[PokemonViewModel::class.java]
        viewModel.listPokemon.observe(this){
            pokemonAdapter.submitList(it)
        }
        pokemonAdapter.onItemClickListener = {
            val id = it.substringAfter("pokemon/").substringBefore("/").toInt()
            launchPokemonDetailFragment(id)
        }
    }

    private fun initRecycler(){
        pokemonAdapter = PokemonNameAdapter()
        binding.mainRv.adapter = pokemonAdapter
    }

    private fun launchPokemonDetailFragment(id:Int){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, PokemonDetailFragment.newInstance(id))
            .commit()
    }

}

