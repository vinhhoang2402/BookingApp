package com.example.bookingapp.ui.base.progress


class EventFireUtil {
    companion object {
        @JvmStatic
        fun fireEvent(event: OnActionNotify?) {
            event?.onActionNotify()
        }
    }
}