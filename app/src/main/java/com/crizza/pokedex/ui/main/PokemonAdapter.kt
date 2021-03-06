package com.crizza.pokedex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.crizza.pokedex.databinding.ItemPokemonBinding
import com.crizza.pokedex.model.main.Pokemon

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(PokemonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewHolder {
                val crewItemBinding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(crewItemBinding)
            }
        }

        fun bind(pokemon: Pokemon) {
            with(binding) {
                itemPokemonImg.load(pokemon.getPokemonImageUrl()) {
                    scale(Scale.FIT)
                }
                itemPokemonName.text = pokemon.name.capitalize()
                root.setOnClickListener { view ->
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pokemon)
                    view.findNavController().navigate(action)
                }
            }
        }
    }

    private class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
            oldItem.name == newItem.name
    }

}