package com.like_magic.pokemonapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.like_magic.pokemonapp.PokemonApp
import com.like_magic.pokemonapp.R
import com.like_magic.pokemonapp.data.network.ConnectivityChecker
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
            if(ConnectivityChecker().isOnline(requireActivity().application)){
                viewModel.loadPokemon(id)
            }
            viewModel.getPokemon(id).observe(viewLifecycleOwner){
                if(it?.name != null){
                    renderData(it)
                }
                else{
                    hideLabels()
                    Snackbar.make(binding.root, R.string.no_data, Snackbar.LENGTH_LONG).show()
                }
            }
        }
        setUpBackBtn()
    }

    private fun hideLabels() {
        with(binding) {
            heightLabel.visibility = INVISIBLE
            weightLabel.visibility = INVISIBLE
            typesLabel.visibility = INVISIBLE
        }
    }

    private fun setUpBackBtn() {
        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack()
        }
    }

    private fun renderData(pokemonEntity: PokemonEntity){
        with(binding){
            name.text = pokemonEntity.name?.uppercase()
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

        private const val POKEMON_ID = "pokemon_id"

        fun newInstance(id:Int) = PokemonDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(POKEMON_ID, id)
            }
        }
    }

}