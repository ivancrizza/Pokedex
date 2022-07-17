package com.crizza.pokedex.data.remote

import com.crizza.pokedex.model.detail.PokemonDetailResponse
import com.crizza.pokedex.model.main.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getSinglePokemon(@Path("name") name: String): PokemonDetailResponse
}