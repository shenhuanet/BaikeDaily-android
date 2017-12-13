package com.shenhua.baikedaily.widget.swipecard

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by shenhua on 2017-12-13-0013.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class SwipeCardItemTouchHelperCallback<T> : ItemTouchHelper.Callback {

    private val adapter: RecyclerView.Adapter<*>
    private var dataList: MutableList<T>? = null
    private var mListener: OnSwipeListener<T>? = null

    constructor(adapter: RecyclerView.Adapter<*>, dataList: MutableList<T>) {
        this.adapter = adapter
        this.dataList = dataList
    }

    constructor(adapter: RecyclerView.Adapter<*>, dataList: MutableList<T>, listener: OnSwipeListener<T>) {
        this.adapter = adapter
        this.dataList = dataList
        this.mListener = listener
    }

    fun setOnSwipedListener(mListener: OnSwipeListener<T>) {
        this.mListener = mListener
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = 0
        var swipeFlags = 0
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is SwipeCardLayoutManager<*>) {
            swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewHolder.itemView.setOnTouchListener(null)
        val layoutPosition = viewHolder.layoutPosition
        val remove = dataList!!.removeAt(layoutPosition)
        adapter.notifyDataSetChanged()
        if (mListener != null) {
            mListener!!.onSwiped(viewHolder, remove,
                    if (direction == ItemTouchHelper.LEFT) SwipeCardConfig.SWIPED_LEFT else SwipeCardConfig.SWIPED_RIGHT)
        }
        if (adapter.itemCount == 0) {
            if (mListener != null) {
                mListener!!.onSwipedClear()
            }
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            var ratio = dX / getThreshold(recyclerView, viewHolder)
            if (ratio > 1) {
                ratio = 1f
            } else if (ratio < -1) {
                ratio = -1f
            }
            itemView.rotation = ratio * SwipeCardConfig.DEFAULT_ROTATE_DEGREE
            val childCount = recyclerView.childCount
            if (childCount > SwipeCardConfig.DEFAULT_ITEM_COUNT) {
                for (position in 1 until childCount - 1) {
                    val index = childCount - position - 1
                    val view = recyclerView.getChildAt(position)
                    view.scaleX = 1 - index * SwipeCardConfig.DEFAULT_SCALE + Math.abs(ratio) * SwipeCardConfig.DEFAULT_SCALE
                    view.scaleY = 1 - index * SwipeCardConfig.DEFAULT_SCALE + Math.abs(ratio) * SwipeCardConfig.DEFAULT_SCALE
                    view.translationY = (index - Math.abs(ratio)) * itemView.measuredHeight / SwipeCardConfig.DEFAULT_TRANSLATE_Y
                }
            } else {
                for (position in 0 until childCount - 1) {
                    val index = childCount - position - 1
                    val view = recyclerView.getChildAt(position)
                    view.scaleX = 1 - index * SwipeCardConfig.DEFAULT_SCALE + Math.abs(ratio) * SwipeCardConfig.DEFAULT_SCALE
                    view.scaleY = 1 - index * SwipeCardConfig.DEFAULT_SCALE + Math.abs(ratio) * SwipeCardConfig.DEFAULT_SCALE
                    view.translationY = (index - Math.abs(ratio)) * itemView.measuredHeight / SwipeCardConfig.DEFAULT_TRANSLATE_Y
                }
            }
            if (mListener != null) {
                if (ratio != 0f) {
                    mListener!!.onSwiping(viewHolder, ratio, if (ratio < 0) SwipeCardConfig.SWIPING_LEFT else SwipeCardConfig.SWIPING_RIGHT)
                } else {
                    mListener!!.onSwiping(viewHolder, ratio, SwipeCardConfig.SWIPING_NONE)
                }
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.rotation = 0f
    }

    private fun getThreshold(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Float
            = recyclerView.width * getSwipeThreshold(viewHolder)

    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

}
