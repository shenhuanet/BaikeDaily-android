package com.shenhua.baikedaily.widget.beziersplash

import android.animation.TypeEvaluator

/**
 * Created by shenhua on 2017-11-24-0024.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class ViewPathEvaluator : TypeEvaluator<ViewPoint> {

    override fun evaluate(fraction: Float, startValue: ViewPoint?, endValue: ViewPoint?): ViewPoint {
        val x: Float
        val y: Float
        var startX: Float
        var startY: Float
        when {
            endValue!!.operation == ViewPath.LINE -> {
                startX = if (startValue!!.operation == ViewPath.QUAD) startValue.x1 else startValue.x
                startX = if (startValue.operation == ViewPath.CURVE) startValue.x2 else startX
                startY = if (startValue.operation == ViewPath.QUAD) startValue.y1 else startValue.y
                startY = if (startValue.operation == ViewPath.CURVE) startValue.y2 else startY
                x = startX + fraction * (endValue.x - startX)
                y = startY + fraction * (endValue.y - startY)
            }
            endValue.operation == ViewPath.CURVE -> {
                startX = if (startValue!!.operation == ViewPath.QUAD) startValue.x1 else startValue.x
                startY = if (startValue.operation == ViewPath.QUAD) startValue.y1 else startValue.y
                val oneMinusT = 1 - fraction
                x = oneMinusT * oneMinusT * oneMinusT * startX +
                        3 * oneMinusT * oneMinusT * fraction * endValue.x +
                        3 * oneMinusT * fraction * fraction * endValue.x1 +
                        fraction * fraction * fraction * endValue.x2
                y = oneMinusT * oneMinusT * oneMinusT * startY +
                        3 * oneMinusT * oneMinusT * fraction * endValue.y +
                        3 * oneMinusT * fraction * fraction * endValue.y1 +
                        fraction * fraction * fraction * endValue.y2
            }
            endValue.operation == ViewPath.MOVE -> {
                x = endValue.x
                y = endValue.y
            }
            endValue.operation == ViewPath.QUAD -> {
                startX = if (startValue!!.operation == ViewPath.CURVE) startValue.x2 else startValue.x
                startY = if (startValue.operation == ViewPath.CURVE) startValue.y2 else startValue.y
                val oneMinusT = 1 - fraction
                x = oneMinusT * oneMinusT * startX +
                        2 * oneMinusT * fraction * endValue.x +
                        fraction * fraction * endValue.x1
                y = oneMinusT * oneMinusT * startY +
                        2 * oneMinusT * fraction * endValue.y +
                        fraction * fraction * endValue.y1
            }
            else -> {
                x = endValue.x
                y = endValue.y
            }
        }
        return ViewPoint(x, y)
    }
}