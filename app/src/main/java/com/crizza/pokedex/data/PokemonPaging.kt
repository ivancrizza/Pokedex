package com.crizza.pokedex.data


import androidx.paging.PagingSource
import com.crizza.pokedex.data.remote.PokeService
import com.crizza.pokedex.model.main.Pokemon
import com.crizza.pokedex.model.main.PokemonResponse
import okio.IOException
import retrofit2.HttpException

private const val START_PAGE_INDEX = 1

class PokemonPaging(
    private val service: PokeService
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: START_PAGE_INDEX
        return try {
            val response = service.getPokemonList()
            val pokemons = response.results
            LoadResult.Page(
                data = pokemons,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (pokemons.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}


