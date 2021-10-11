package com.example.bookingapp.common.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableUseCase<T>(
        private val executorThread : Scheduler,
        private val uiThread : Scheduler
) {

    operator fun invoke() : Observable<T>{
        return create().subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create() : Observable<T>
}