package com.shenhua.baikedaily.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout


/**
 * Created by shenhua on 2017-11-23-0023.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class SplashView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    val size: Float = 160f;
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mIsStart: Boolean = false
    private var iv1: ImageView? = null
    private var iv2: ImageView? = null
    private var iv3: ImageView? = null
    private var iv4: ImageView? = null
    private var path1: ViewPath? = null
    private var path2: ViewPath? = null
    private var path3: ViewPath? = null
    private var path4: ViewPath? = null
    private var sets: ArrayList<AnimatorSet>? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        initPath()
    }

    private fun initPath() {
        path1 = ViewPath()
        path1!!.moveTo(0f, 0f);
        path1!!.lineTo(mWidth / 5 - mWidth / 2f, 0f);
        path1!!.curveTo(-700f, -mHeight / 2f, mWidth / 3 * 2f, -mHeight / 3 * 2f, 0f, size)

        path2 = ViewPath()
        path2!!.moveTo(0f, 0f)
        path2!!.lineTo(mWidth / 5 * 2 - mWidth / 2f, 0f)
        path2!!.curveTo(-300f, -mHeight / 2f, mWidth.toFloat(), -mHeight / 9 * 5f, 0f, -size)

        path3 = ViewPath()
        path3!!.moveTo(0f, 0f)
        path3!!.lineTo(mWidth / 5 * 3 - mWidth / 2f, 0f)
        path3!!.curveTo(300f, mHeight.toFloat(), -mWidth.toFloat(), -mHeight / 9 * 5f, 0f, -size)

        path4 = ViewPath()
        path4!!.moveTo(0f, 0f)
        path4!!.lineTo(mWidth / 5 * 4 - mWidth / 2f, 0f)
        path4!!.curveTo(700f, mHeight / 3 * 2f, -mWidth / 2f, mHeight / 2f, 0f, -size)
    }

    public fun start() {
        init()
        sets!!.forEach {
            it.start()
        }
        Handler().postDelayed(Runnable { showLogo() }, 2400)
    }

    private fun showLogo() {


    }

    private fun init() {
        removeAllViews()
        val lp: RelativeLayout.LayoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.addRule(CENTER_HORIZONTAL, TRUE)
        lp.addRule(CENTER_VERTICAL, TRUE)
        lp.setMargins(0, 0, 0, size.toInt())

    }


    private inner class AnimEndListener : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
        }
    }

    inner class ViewObj {

    }
}
