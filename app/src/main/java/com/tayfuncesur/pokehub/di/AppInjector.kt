package com.tayfuncesur.pokehub.di

import com.tayfuncesur.pokehub.App

object AppInjector {
    fun init(app: App) {
        DaggerAppComponent.builder().application(app)
            .build().inject(app)
    }
}
