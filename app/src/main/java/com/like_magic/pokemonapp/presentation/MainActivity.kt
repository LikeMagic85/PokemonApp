package com.like_magic.pokemonapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.like_magic.pokemonapp.PokemonApp
import com.like_magic.pokemonapp.R
import com.like_magic.pokemonapp.databinding.ActivityMainBinding
import com.like_magic.pokemonapp.presentation.adapter.PokemonNameAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel:PokemonViewModel
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
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[PokemonViewModel::class.java]
        observeViewModel()
        setOnItemClickListener()
        loadMorePokemon()
    }

    private fun observeViewModel() {
        viewModel.isOnline.observe(this) {
            if (!it) {
                Snackbar.make(binding.root, R.string.connection_false, Snackbar.LENGTH_LONG).show()
            }
        }
        viewModel.listPokemon.observe(this) {
            pokemonAdapter.submitList(it)
        }
    }

    private fun setOnItemClickListener() {
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

    private fun loadMorePokemon(){
        binding.nextPageBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.loadNextPage()
            }
        }
    }


}

