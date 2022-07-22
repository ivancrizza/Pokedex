package com.crizza.pokedex

import com.crizza.pokedex.data.repository.DetailRepository
import com.crizza.pokedex.data.repository.MainRepository
import com.crizza.pokedex.model.detail.PokemonDetailResponse
import com.crizza.pokedex.model.main.PokemonResponse
import com.crizza.pokedex.utils.Result
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when trying to load pokemon on MainRepo, and the result its ok`() = runBlocking {
        val repository: MainRepository = mockk()
        val response: Flow<Result<PokemonResponse>> = mockk()
        every { repository.getPokemonList() }.returns(response)
    }

    @Test
    fun `when trying to load pokemon on DetailRepo, and the result its ok`() = runBlocking {
        val pokeName = "Pikachu"
        val repo: DetailRepository = mockk()
        val response: Flow<Result<PokemonDetailResponse>> = mockk()
        every { repo.getSinglePokemon(pokeName) }.returns(response)
    }
}