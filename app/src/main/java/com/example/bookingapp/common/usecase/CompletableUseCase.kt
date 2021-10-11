package com.example.bookingapp.common.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase(
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
) {

    operator fun invoke(): Completable {
        return create().subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create(): Completable
}