package com.tayfuncesur.pokehub.ui.splash

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.CountDownTimer
import com.tayfuncesur.pokehub.util.SPLASH_SCREEN_DELAY
import javax.inject.Inject


class SplashScreenViewModel @Inject constructor() : ViewModel() {

    val isFinished = MutableLiveData<Boolean>()

    init {
        object : CountDownTimer(SPLASH_SCREEN_DELAY, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                isFinished.postValue(true)
            }

        }.start()
    }
}