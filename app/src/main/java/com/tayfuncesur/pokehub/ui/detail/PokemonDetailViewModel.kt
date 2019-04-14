package com.tayfuncesur.pokehub.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tayfuncesur.pokehub.PokemonDetailRepositoryQuery
import com.tayfuncesur.pokehub.PokemonRepositoryQuery
import com.tayfuncesur.pokehub.base.Resource
import com.tayfuncesur.pokehub.repository.PokemonDetailRepository
import com.tayfuncesur.pokehub.repository.PokemonRepository
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val pokemonDetailRepository: PokemonDetailRepository) :
    ViewModel() {

    fun getPokemonDetail(id: String): LiveData<Resource<PokemonDetailRepositoryQuery.Pokemon>> {
        return pokemonDetailRepository.getPokemonDetail(id)
    }

    override fun onCleared() {
        pokemonDetailRepository.clear()
        super.onCleared()
    }

}