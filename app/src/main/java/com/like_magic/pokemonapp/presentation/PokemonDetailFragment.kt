package com.like_magic.pokemonapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.like_magic.pokemonapp.PokemonApp
import com.like_magic.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.like_magic.pokemonapp.domain.entity.PokemonEntity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailFragment:Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PokemonViewModel::class.java]
    }

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding: FragmentPokemonDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentPokemonDetailBinding is null")

    private val component by lazy {
        (requireActivity().application as PokemonApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = parseArgs()
        lifecycleScope.launch {
            viewModel.loadPokemon(id)
            viewModel.getPokemon(id).observe(viewLifecycleOwner){
                renderData(it)
            }
        }
        setUpBackBtn()
    }

    private fun setUpBackBtn() {
        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack()
        }
    }

    private fun renderData(pokemonEntity: PokemonEntity){
        with(binding){
            name.text = pokemonEntity.name
            Picasso.get().load(pokemonEntity.imgUrl).into(pokemonImg)
            heightValue.text = pokemonEntity.height.toString()
            weightValue.text = pokemonEntity.weight.toString()
            typesValue.text = pokemonEntity.type
        }
    }

    private fun parseArgs(): Int {
        return arguments?.getInt(POKEMON_ID) ?: 1
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        lifecycleScope.cancel()
    }

    companion object {

        private const val POKEMON_ID = "pokemon_url"

        fun newInstance(id:Int) = PokemonDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(POKEMON_ID, id)
            }
        }
    }

}