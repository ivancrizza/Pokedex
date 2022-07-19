package com.crizza.pokedex.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.crizza.pokedex.R
import com.crizza.pokedex.databinding.FragmentDetailBinding
import com.crizza.pokedex.model.detail.TypePoke
import com.crizza.pokedex.utils.Result
import com.crizza.pokedex.utils.extensions.gone
import com.crizza.pokedex.utils.extensions.toast
import com.crizza.pokedex.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()
    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.detailName?.text = args.pokemon.name.capitalize()

        viewModel.getSinglePokemon(args.pokemon.name)
        lifecycleScope.launch {
            viewModel.pokemon.collect { result ->
                when (result) {
                    is Result.Loading -> {
                        binding?.detailProgressBar?.visible()
                    }
                    is Result.Success -> {
                        with(binding) {
                            this?.let {
                                val resultType = result.data.types[0].type.name.capitalize()
                                detailType.text = resultType
                                setColors(resultType)
                                detailProgressBar.gone()
                                detailExp.text = result.data.baseExp.toString()
                                detailHeight.text = "${result.data.height.toDouble() / 10} M"
                                detailWeight.text = "${result.data.weight.toDouble() / 10} KG"
                                imageView.load(args.pokemon.getPokemonImageUrl())
                            }
                        }
                    }
                    is Result.Error -> {
                        activity?.toast("Something went wrong. Please try again later")
                        Log.e("HomeFragment", result.exception.toString())
                    }
                }
            }
        }
    }


    private fun setColors(pokeType: String) {
        when (pokeType) {
            TypePoke.ELECTRIC.type -> setBackGroundColor(R.color.electric_type_background)
            TypePoke.FIRE.type -> setBackGroundColor(R.color.fire_type_background)
            TypePoke.GRASS.type -> setBackGroundColor(R.color.grass_type_background)
            TypePoke.WATER.type -> setBackGroundColor(R.color.water_type_background)
            TypePoke.PSYCHIC.type -> setBackGroundColor(R.color.psychic_type_background)
            TypePoke.BUG.type -> setBackGroundColor(R.color.bug_type_background)
            TypePoke.FLYING.type -> setBackGroundColor(R.color.flying_type_background)
            TypePoke.GHOST.type -> setBackGroundColor(R.color.ghost_type_background)
            TypePoke.NORMAL.type -> setBackGroundColor(R.color.normal_type_background)
            TypePoke.ROCK.type -> setBackGroundColor(R.color.rock_type_background)
            TypePoke.FIGHTING.type -> setBackGroundColor(R.color.fighting_type_background)
            TypePoke.POISON.type -> setBackGroundColor(R.color.poison_type_background)
            TypePoke.GROUND.type -> setBackGroundColor(R.color.ground_type_background)
            TypePoke.ICE.type -> setBackGroundColor(R.color.ice_type_background)
            TypePoke.DRAGON.type -> setBackGroundColor(R.color.dragon_type_background)
        }
    }

    private fun setBackGroundColor(electricColor: Int) =
        binding?.containerDetail?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                electricColor
            )
        )
}