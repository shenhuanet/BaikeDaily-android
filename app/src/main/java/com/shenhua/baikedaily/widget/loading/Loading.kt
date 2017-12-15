package com.shenhua.baikedaily.widget.loading

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by shenhua on 2017-12-14-0014.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class Loading @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                        defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    @Volatile private var mLength = 0
    private var mRadius = dp2px(getContext(), 10f).toInt()
    private var mLineWidth = dp2px(getContext(), 5f).toInt()
    private var isStart: Boolean = false
    private var mExecutor: ScheduledExecutorService? = null
    private var mDuration = 25

    init {
        visibility = GONE
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.color = Color.BLUE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.rotate((180 * mLength / 50).toFloat(), mWidth / 2f, mHeight / 2f)

        mLength = if (mLength <= (mWidth / 2 - mRadius) / 2) {
            mLength * 2
        } else {
            (mWidth / 2 - mRadius - mLength) * 2
        }

        val p1x = mWidth / 2f
        val p1y = mHeight / 2f
        val p2x = p1x - (mLength * Math.cos(Math.PI * 30.0 / 180.0)).toFloat()
        val p2y = p1y - (mLength * Math.sin(Math.PI * 30.0 / 180.0)).toFloat()
        val p3x = p1x + (mLength * Math.cos(Math.PI * 30.0 / 180.0)).toFloat()
        val p3y = p1y - (mLength * Math.sin(Math.PI * 30.0 / 180.0)).toFloat()

        val paint = mPaint
        paint!!.color = -0xcd65af
        paint.strokeWidth = mLineWidth.toFloat()
        canvas.drawLine(p1x, p1y + mLength, p2x, p2y, paint)
        canvas.drawLine(p2x, p2y, p3x, p3y, paint)
        canvas.drawLine(p1x, p1y + mLength, p3x, p3y, paint)

        paint.strokeWidth = 0f
        canvas.drawCircle(p1x, p1y + mLength, mRadius.toFloat(), paint)
        canvas.drawCircle(p2x, p2y, mRadius.toFloat(), paint)
        canvas.drawCircle(p3x, p3y, mRadius.toFloat(), paint)
    }

    fun start() {
        if (isStart) {
            return
        }
        visibility = VISIBLE
        mExecutor = Executors.newScheduledThreadPool(1)
        mExecutor!!.scheduleAtFixedRate({
            for (i in 0..mWidth / 2 - mRadius) {
                try {
                    Thread.sleep(mDuration.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                postLength(i)
            }
        }, 0, 1000, TimeUnit.MILLISECONDS)
        isStart = true
    }

    fun stop() {
        if (isStart) {
            visibility = GONE
            mExecutor!!.shutdown()
            isStart = false
        }
    }

    private fun postLength(length: Int) {
        this.mLength = length
        postInvalidate()
    }

    /**
     * 持续时间
     *
     * @param duration MILLISECONDS
     */
    fun setDuration(duration: Int) {
        this.mDuration = duration
    }

    /**
     * 线长
     *
     * @param length px
     */
    fun setLength(length: Int) {
        this.mLength = length
        invalidate()
    }

    /**
     * 圆半径
     *
     * @param radius dp
     */
    fun setRadius(radius: Int) {
        this.mRadius = dp2px(context, radius.toFloat()).toInt()
    }

    /**
     * 线宽
     *
     * @param lineWidth dp
     */
    fun setLineWidth(lineWidth: Int) {
        this.mLineWidth = dp2px(context, lineWidth.toFloat()).toInt()
    }

    private fun dp2px(context: Context, dip: Float): Float {
        val displayMetrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics)
    }

}
