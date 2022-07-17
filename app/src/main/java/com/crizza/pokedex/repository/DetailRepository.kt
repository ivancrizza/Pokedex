package com.crizza.pokedex.repository

import com.crizza.pokedex.data.remote.PokeService
import com.crizza.pokedex.model.detail.PokemonDetailResponse
import com.crizza.pokedex.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val apiService: PokeService
) {

    fun getSinglePokemon(name: String): Flow<Result<PokemonDetailResponse>> {
        return flow {
            emit(Result.Loading)
            try {
                val pokemon = apiService.getSinglePokemon(name)
                emit(Result.Success(pokemon))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    }
}