package com.tayfuncesur.pokehub.di.module

import com.tayfuncesur.pokehub.ui.main.MainActivity
import com.tayfuncesur.pokehub.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity() : SplashActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity() : MainActivity


}