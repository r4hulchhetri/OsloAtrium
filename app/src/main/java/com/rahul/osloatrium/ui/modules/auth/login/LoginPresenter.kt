package com.rahul.osloatrium.ui.modules.auth.login

import android.text.TextUtils
import android.util.Patterns
import com.rahul.osloatrium.model.request.LoginBody
import com.rahul.osloatrium.rest.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter : LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    private val loginService = ApiFactory.loginService

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {

    }

    override fun attemptLogin(username: String, password: String) {
        if (!isValidUserName(username) && !isValidPassword(password)) {
            view.showUserNameInvalid()
            view.showPasswordNonEmpty()
            return
        }

        if (!isValidUserName(username)) {
            view.showUserNameInvalid()
            return
        }

        if (!isValidPassword(password)) {
            view.showPasswordNonEmpty()
            return
        }

        view.showProgress(true)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = loginService.login(LoginBody(username, password))
                withContext(Dispatchers.Main) {
                    view.showProgress(false)
                    if (userResponse.isSuccessful) {
                        view.doOnLoginSuccess(userResponse.body()?.body)
                    } else {
                        view.showErrorMessage(userResponse.body()?.status?.message)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    view.showErrorMessage(e.message)
                    view.showProgress(false)
                }
            }
        }
    }

    private fun isValidUserName(username: String): Boolean {
        return !TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}