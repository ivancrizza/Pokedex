package com.crizza.pokedex.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crizza.pokedex.model.detail.PokemonDetailResponse
import com.crizza.pokedex.data.repository.DetailRepository
import com.crizza.pokedex.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val repository: DetailRepository
): ViewModel() {

    private val _pokemon = MutableStateFlow<Result<PokemonDetailResponse>>(Result.Loading)
    val pokemon: StateFlow<Result<PokemonDetailResponse>> = _pokemon

    fun getSinglePokemon(name: String) {
        repository.getSinglePokemon(name)
            .onEach {
                _pokemon.value = it
            }
            .launchIn(viewModelScope)
    }
}