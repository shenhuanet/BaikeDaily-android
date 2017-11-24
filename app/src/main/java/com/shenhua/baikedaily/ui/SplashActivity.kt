package com.shenhua.baikedaily.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.widget.SplashView

/**
 * Created by shenhua on 2017-11-24-0024.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class SplashActivity : AppCompatActivity(), SplashView.OnAnimEndListener {

    private var mLogoView: TextView? = null

    /**
     * When the SplashView Anim ended,we show the app logo.
     */
    override fun onEnd() {
        mLogoView = findViewById(R.id.logoView)
        val anim = ObjectAnimator.ofFloat(mLogoView, View.ALPHA, 0f, 1f)
        anim.duration = 1000
        anim.start()
        mLogoView!!.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    private var mSplashView: SplashView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        mSplashView = findViewById(R.id.splashView)
        mSplashView!!.mEndListener = this
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            mSplashView!!.start();
        }, 1000)
    }
}