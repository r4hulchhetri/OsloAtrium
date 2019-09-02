package com.rahul.osloatrium.ui.modules.splash

import android.content.Intent
import android.os.Handler
import com.rahul.osloatrium.Constants
import com.rahul.osloatrium.R
import com.rahul.osloatrium.ui.base.BaseActivity
import com.rahul.osloatrium.ui.modules.auth.login.LoginActivity

class SplashActivity : BaseActivity() {

    private val splashIntentRunnable = Runnable { handleIntent() }

    private var handler = Handler()

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(splashIntentRunnable, Constants.SPLASH_DELAY)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(splashIntentRunnable)
    }

    private fun handleIntent() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}