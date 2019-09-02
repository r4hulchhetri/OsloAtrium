package com.rahul.osloatrium.ui.modules.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rahul.osloatrium.R
import com.rahul.osloatrium.model.response.User
import com.rahul.osloatrium.ui.base.BaseActivity
import com.rahul.osloatrium.ui.modules.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {
    lateinit var presenter: LoginContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter()
        presenter.attach(this)

        btnLogin.setOnClickListener {
            presenter.attemptLogin(
                edUsername.text.toString().trim(),
                edPassword.text.toString().trim()
            )
        }
    }

    override fun showUserNameInvalid() {
        edUsername.error = "Invalid e-mail"
    }

    override fun showPasswordNonEmpty() {
        edPassword.error = "Invalid password"
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            btnLogin.visibility = View.GONE
            progress.visibility = View.VISIBLE
        } else {
            btnLogin.visibility = View.VISIBLE
            progress.visibility = View.GONE
        }
    }

    override fun showErrorMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun doOnLoginSuccess(user: User?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}