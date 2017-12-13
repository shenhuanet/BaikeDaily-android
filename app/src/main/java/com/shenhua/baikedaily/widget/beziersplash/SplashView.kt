package com.shenhua.baikedaily.widget.beziersplash

import android.animation.*
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.shenhua.baikedaily.R


/**
 * Created by shenhua on 2017-11-23-0023.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class SplashView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    companion object {
        val CIRCLE_SIZE = 4
        val TAG = "SplashView"
        val SIZE = 160f
    }

    var mEndListener: OnAnimEndListener? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mPaths = arrayOfNulls<ViewPath>(CIRCLE_SIZE)
    private var mSets = arrayOfNulls<AnimatorSet>(CIRCLE_SIZE)
    private var mImageViews = arrayOfNulls<ImageView>(CIRCLE_SIZE)
    private var mImages = arrayOf(R.drawable.ic_circle1, R.drawable.ic_circle2, R.drawable.ic_circle3, R.drawable.ic_circle4)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        initPaths()
    }

    private fun initPaths() {
        for (i in mPaths.indices) {
            mPaths[i] = ViewPath()
            mPaths[i]!!.moveTo(0f, 0f)
            when (i) {
                0 -> {
                    mPaths[0]!!.lineTo(mWidth / 5 - mWidth / 2f, 0f)
                    mPaths[0]!!.curveTo(-700f, -mHeight / 2f, mWidth / 3 * 2f, -mHeight / 3 * 2f, 0f, -SIZE)
                }
                1 -> {
                    mPaths[1]!!.lineTo(mWidth / 5 * 2 - mWidth / 2f, 0f)
                    mPaths[1]!!.curveTo(-300f, -mHeight / 2f, mWidth.toFloat(), -mHeight / 9 * 5f, 0f, -SIZE)
                }
                2 -> {
                    mPaths[2]!!.lineTo(mWidth / 5 * 3 - mWidth / 2f, 0f)
                    mPaths[2]!!.curveTo(300f, mHeight.toFloat(), -mWidth.toFloat(), -mHeight / 9 * 5f, 0f, -SIZE)
                }
                3 -> {
                    mPaths[3]!!.lineTo(mWidth / 5 * 4 - mWidth / 2f, 0f)
                    mPaths[3]!!.curveTo(700f, mHeight / 3 * 2f, -mWidth / 2f, mHeight / 2f, 0f, -SIZE)
                }
            }
        }
    }

    fun start() {
        initViews()
        mSets.forEach {
            it!!.start()
        }
    }

    private fun initViews() {
        removeAllViews()
        val lp: RelativeLayout.LayoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.addRule(CENTER_HORIZONTAL, TRUE)
        lp.addRule(CENTER_VERTICAL, TRUE)
        lp.setMargins(0, 0, 0, SIZE.toInt())
        for (i in mImageViews.indices) {
            mImageViews[i] = ImageView(context)
            mImageViews[i]!!.id = mImageViews.hashCode() + i
            mImageViews[i]!!.layoutParams = lp
            mImageViews[i]!!.setImageResource(mImages[i])
            addView(mImageViews[i]!!)
            mSets[i] = AnimatorSet()
            setAnim(mImageViews[i]!!, mPaths[i]!!, mSets[i]!!, i)
        }
    }

    private fun setAnim(target: ImageView, path: ViewPath, animatorSet: AnimatorSet, index: Int) {
        val anim = ObjectAnimator.ofObject(ViewObj(target), "fabLoc", ViewPathEvaluator(), *path.points.toArray())
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.duration = 2600
        val valueAnimator = ValueAnimator.ofFloat(1f, 1000f)
        valueAnimator.duration = 1800
        valueAnimator.startDelay = 1000
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            val alpha = 1 - value / 2000
            var scale = when (index) {
                0 -> 3.0f
                1 -> 2.0f
                2 -> 4.5f
                3 -> 3.5f
                else -> 2f
            } - 1
            scale = if (value <= 500) {
                1 + value / 500 * scale
            } else {
                1 + (1000 - value) / 500 * scale
            }
            target.scaleX = scale
            target.scaleY = scale
            target.alpha = alpha
        }
        valueAnimator.addListener(AnimEndListener(target, index))
        animatorSet.playTogether(anim, valueAnimator)
    }

    private inner class AnimEndListener(val target: ImageView, val index: Int) : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            removeView(target)
            if (index == CIRCLE_SIZE - 1) {
                mEndListener!!.onEnd()
            }
        }
    }

    inner class ViewObj(private val iv: ImageView) {
        /**
         * Never Use
         */
        fun setFabLoc(newLoc: ViewPoint) {
            iv.translationX = newLoc.x
            iv.translationY = newLoc.y
        }
    }

    interface OnAnimEndListener {
        fun onEnd()
    }
}
