package com.tayfuncesur.pokehub.base

import io.reactivex.disposables.Disposable

interface Repository {

    fun addDisposable(disposable: Disposable)

    fun clear()

}