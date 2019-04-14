package com.tayfuncesur.pokehub.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tayfuncesur.pokehub.PokemonRepositoryQuery
import com.tayfuncesur.pokehub.base.Resource
import com.tayfuncesur.pokehub.repository.PokemonRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    lateinit var pokemonLiveData: LiveData<Resource<List<PokemonRepositoryQuery.Pokemon>>>

    init {
        getPokemons()
    }

    private fun getPokemons() {
        pokemonLiveData = pokemonRepository.getPokemons(200)
    }

    override fun onCleared() {
        pokemonRepository.clear()
        super.onCleared()
    }

}