package com.tayfuncesur.pokehub.base

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tayfuncesur.pokehub.R
import dagger.android.AndroidInjection

open class BaseDaggerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    fun showSnackbar(
        view: View,
        message: String,
        type: Int,
        duration: Int = Snackbar.LENGTH_SHORT,
        callback: () -> Unit
    ) {
        val snackbar = Snackbar
            .make(view, message, duration)
        when (type) {
            1 -> snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.successColor))
            2 -> snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.errColor))
            else -> snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.yellowWarning))
        }
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                callback()
            }
        })
        snackbar.show()
    }


}