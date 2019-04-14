package com.tayfuncesur.pokehub.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.tayfuncesur.pokehub.R
import com.tayfuncesur.pokehub.base.BaseDaggerActivity
import com.tayfuncesur.pokehub.ui.main.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


class SplashActivity : BaseDaggerActivity() {

    private lateinit var splashScreenViewModel: SplashScreenViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash_screen)
        AndroidInjection.inject(this)

        splashScreenViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)

        splashScreenViewModel.isFinished.observe(this, Observer {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        })
    }
}
