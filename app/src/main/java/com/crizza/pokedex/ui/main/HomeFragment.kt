package com.crizza.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.crizza.pokedex.databinding.FragmentHomeBinding
import com.crizza.pokedex.utils.Result
import com.crizza.pokedex.utils.extensions.gone
import com.crizza.pokedex.utils.extensions.toast
import com.crizza.pokedex.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonAdapter()
        binding?.pokemonRecyclerview?.adapter = adapter

        viewModel.getPokemonList()

        lifecycleScope.launch {
            viewModel.pokemonList.collect { result ->
                when(result) {
                    is Result.Loading -> {
                        binding?.progressbar?.visible()
                        binding?.pokemonRecyclerview?.gone()
                    }
                    is Result.Success -> {
                        binding?.progressbar?.gone()
                        binding?.pokemonRecyclerview?.visible()
                        adapter.submitList(result.data.results)
                    }
                    is Result.Error -> {
                        activity?.toast("Something went wrong. Please try again later")
                        Log.e("HomeFragment", result.exception.toString())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}