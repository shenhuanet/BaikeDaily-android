package com.shenhua.baikedaily.widget

/**
 * Created by shenhua on 2017-11-23-0023.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class ViewPoint {

    private var x: Float = 0.toFloat()
    private var y: Float = 0.toFloat()
    private var x1: Float = 0.toFloat()
    private var y1: Float = 0.toFloat()
    private var x2: Float = 0.toFloat()
    private var y2: Float = 0.toFloat()
    private var operation: Int = 0

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    private constructor(x: Float, y: Float, operation: Int) {
        this.x = x
        this.y = y
        this.operation = operation
    }

    constructor(x: Float, y: Float, x1: Float, y1: Float, operation: Int) {
        this.x = x
        this.y = y
        this.x1 = x1
        this.y1 = y1
        this.operation = operation
    }

    constructor(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float, operation: Int) {
        this.x = x
        this.y = y
        this.x1 = x1
        this.y1 = y1
        this.x2 = x2
        this.y2 = y2
        this.operation = operation
    }

    companion object {

        fun moveTo(x: Float, y: Float, operation: Int): ViewPoint {
            return ViewPoint(x, y, operation)
        }

        fun lineTo(x: Float, y: Float, operation: Int): ViewPoint {
            return ViewPoint(x, y, operation)
        }

        fun curveTo(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float, operation: Int): ViewPoint {
            return ViewPoint(x, y, x1, y1, x2, y2, operation)
        }

        fun quadTo(x: Float, y: Float, x1: Float, y1: Float, operation: Int): ViewPoint {
            return ViewPoint(x, y, x1, y1, operation)
        }
    }
}