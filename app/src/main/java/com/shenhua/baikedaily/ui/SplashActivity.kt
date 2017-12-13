package com.shenhua.baikedaily.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.widget.beziersplash.SplashView
import kotlinx.android.synthetic.main.activity_spalsh.*

/**
 * Created by shenhua on 2017-11-24-0024.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class SplashActivity : AppCompatActivity(), SplashView.OnAnimEndListener {

    /**
     * When the SplashView Anim ended,we show the app logo.
     */
    override fun onEnd() {
        val anim = ObjectAnimator.ofFloat(logoView, View.ALPHA, 0f, 1f)
        anim.duration = 1000
        anim.start()
        logoView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        splashView.mEndListener = this
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            splashView.start();
        }, 1000)
    }
}