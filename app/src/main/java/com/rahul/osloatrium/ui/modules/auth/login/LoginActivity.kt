package com.rahul.osloatrium.ui.modules.auth.login

import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.rahul.osloatrium.R
import com.rahul.osloatrium.model.request.LoginBody
import com.rahul.osloatrium.rest.ApiFactory
import com.rahul.osloatrium.ui.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginService = ApiFactory.loginService

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    loginService.login(LoginBody("tester.smartmobe@gmail.com", "NorwayWC2019"))
                        .await()
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {

                        } else {
                            println(Gson().toJson(response))
                            Toast.makeText(
                                this@LoginActivity,
                                response.body()?.status?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}