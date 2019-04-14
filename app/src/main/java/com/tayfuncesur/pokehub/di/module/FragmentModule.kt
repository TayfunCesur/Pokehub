package com.tayfuncesur.pokehub.di.module

import com.tayfuncesur.pokehub.ui.detail.PokemonDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePokemonDetailFragment(): PokemonDetailFragment
}
