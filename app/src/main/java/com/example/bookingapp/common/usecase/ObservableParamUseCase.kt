package com.example.bookingapp.common.usecase

import android.renderscript.ScriptGroup
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class ObservableParamUseCase<T, in Param>(
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
) {
    private var disposables: CompositeDisposable = CompositeDisposable()

    operator fun invoke(param: Param): Observable<T> {
        return create(param).subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create(param: Param): Observable<T>
}