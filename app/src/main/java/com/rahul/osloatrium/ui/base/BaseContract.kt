package com.rahul.osloatrium.ui.base

class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unSubscribe()
        fun attach(view: T)
    }

    interface View {}
}