package com.tayfuncesur.pokehub.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tayfuncesur.pokehub.base.ViewModelFactory
import com.tayfuncesur.pokehub.base.ViewModelKey
import com.tayfuncesur.pokehub.ui.detail.PokemonDetailViewModel
import com.tayfuncesur.pokehub.ui.splash.SplashScreenViewModel
import com.tayfuncesur.pokehub.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    internal abstract fun bindSplashViewModel(splashScreenViewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    internal abstract fun bindPokemonDetailViewModel(pokemonDetailViewModel: PokemonDetailViewModel): ViewModel

}