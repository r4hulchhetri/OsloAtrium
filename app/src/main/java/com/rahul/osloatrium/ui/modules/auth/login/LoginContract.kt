package com.rahul.osloatrium.ui.modules.auth.login

import com.rahul.osloatrium.model.response.User
import com.rahul.osloatrium.ui.base.BaseContract

class LoginContract {

    interface View : BaseContract.View {
        fun showUserNameInvalid()
        fun showPasswordNonEmpty()
        fun showProgress(show: Boolean)
        fun showErrorMessage(errorMessage: String?)
        fun doOnLoginSuccess(user: User?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun attemptLogin(username: String, password: String)
    }
}