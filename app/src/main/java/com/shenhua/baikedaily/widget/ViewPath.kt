package com.shenhua.baikedaily.widget

/**
 * Created by shenhua on 2017-11-23-0023.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class ViewPath {

    private val mPoints: ArrayList<ViewPoint> = ArrayList()

    val points: Collection<ViewPoint>
        get() = mPoints

    fun moveTo(x: Float, y: Float) {
        mPoints.add(ViewPoint.moveTo(x, y, MOVE))
    }

    fun lineTo(x: Float, y: Float) {
        mPoints.add(ViewPoint.lineTo(x, y, LINE))
    }

    fun curveTo(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float) {
        mPoints.add(ViewPoint.curveTo(x, y, x1, y1, x2, y2, CURVE))
    }

    fun quadTo(x: Float, y: Float, x1: Float, y1: Float) {
        mPoints.add(ViewPoint.quadTo(x, y, x1, y1, QUAD))
    }

    companion object {
        val MOVE = 0
        val LINE = 1
        val QUAD = 2
        val CURVE = 3
    }
}