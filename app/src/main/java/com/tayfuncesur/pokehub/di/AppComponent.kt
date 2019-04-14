package com.tayfuncesur.pokehub.di

import android.app.Application
import com.tayfuncesur.pokehub.App
import com.tayfuncesur.pokehub.di.module.AppModule
import com.tayfuncesur.pokehub.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}