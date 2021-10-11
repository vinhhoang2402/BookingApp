package com.example.bookingapp.common.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableParamUseCase<T, in Param>(
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
) {

    operator fun invoke(param: Param): Observable<T> {
        return create(param).subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create(param: Param): Observable<T>
}