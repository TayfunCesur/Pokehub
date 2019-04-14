package com.tayfuncesur.pokehub.ui.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tayfuncesur.pokehub.PokemonDetailRepositoryQuery
import com.tayfuncesur.pokehub.R
import com.tayfuncesur.pokehub.base.Resource
import com.tayfuncesur.pokehub.databinding.PokemonDetailBinding
import com.tayfuncesur.pokehub.ui.main.MainAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.pokemon_detail.*
import javax.inject.Inject

class PokemonDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    lateinit var binding: PokemonDetailBinding

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"

        fun newInstance(pokemonId: String) = PokemonDetailFragment().apply {
            arguments = Bundle(1).apply {
                putString(POKEMON_ID, pokemonId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_detail, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        pokemonDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(PokemonDetailViewModel::class.java)
        val liveData = arguments?.getString(POKEMON_ID)?.let { pokemonDetailViewModel.getPokemonDetail(it) }
        binding.data = liveData
        binding.lifecycleOwner = viewLifecycleOwner


        liveData?.observe(viewLifecycleOwner, Observer {
            if (it is Resource.Success) {
                toolbarText.text = it.data?.name()
                Glide.with(this).load(it.data?.image()).into(image)
                weight.text = " Max: ${it.data?.weight()?.maximum()}\n Min: ${it.data?.weight()?.minimum()}"
                height.text = " Max: ${it.data?.height()?.maximum()}\n Min: ${it.data?.height()?.minimum()}"
                var resistanceString = ""
                for (item in it.data?.resistant()!!) {
                    resistanceString += "$item,"
                }
                resistance.text = resistanceString

                var specialAttacksString = ""
                for (item in it.data.attacks()?.special()!!) {
                    specialAttacksString += "${item.name()},"
                }
                specialAttacks.text = specialAttacksString

                var weaknessesString = ""
                for (item in it.data.weaknesses()!!) {
                    weaknessesString += "$item,"
                }
                weaknesses.text = weaknessesString
                if (it.data.evolutions() == null) {
                    evolutionLabel.visibility = View.GONE
                }
                evolutionRecycler.layoutManager =
                    LinearLayoutManager(this@PokemonDetailFragment.context, LinearLayoutManager.HORIZONTAL, false)
                evolutionRecycler.adapter =
                    it.data.evolutions()?.let { it1 ->
                        PokemonEvulotionAdapter(it1) { pokemon ->
                            newInstance(pokemon.id()).let { it3 ->
                                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                                    android.R.anim.fade_in,
                                    android.R.anim.fade_out,
                                    android.R.anim.fade_in,
                                    android.R.anim.fade_out
                                )?.add(
                                    R.id.mainActivityRoot,
                                    it3
                                )?.addToBackStack(PokemonDetailFragment::class.java.name)?.commit()
                            }
                        }
                    }
            }
        })
    }

}