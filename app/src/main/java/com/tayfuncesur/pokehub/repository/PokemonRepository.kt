package com.tayfuncesur.pokehub.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tayfuncesur.pokehub.PokemonRepositoryQuery
import com.tayfuncesur.pokehub.base.Repository
import com.tayfuncesur.pokehub.base.Resource
import com.tayfuncesur.pokehub.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepository @Inject constructor(private var apiService: ApiService) : Repository {

    private val compositeDisposable = CompositeDisposable()

    private val result = MutableLiveData<Resource<List<PokemonRepositoryQuery.Pokemon>>>()

    fun getPokemons(count: Int): LiveData<Resource<List<PokemonRepositoryQuery.Pokemon>>> {
        addDisposable(
            apiService.getPokemons(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { whenStart() }
                .subscribe(
                    { result -> whenSuccess(result) },
                    { cause -> whenError(cause.toString()) }
                )
        )

        return result
    }

    private fun whenStart() {
        result.value = Resource.Loading()
    }

    fun whenSuccess(pokemonList: List<PokemonRepositoryQuery.Pokemon>) {
        result.value = Resource.Success(pokemonList)
    }

    fun whenError(cause: String) {
        result.value = Resource.Failure(cause)
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}