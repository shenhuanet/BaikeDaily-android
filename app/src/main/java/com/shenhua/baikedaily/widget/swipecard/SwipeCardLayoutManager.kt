package com.shenhua.baikedaily.widget.swipecard

import android.os.Build
import android.support.annotation.NonNull
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

/**
 * Created by shenhua on 2017-12-13-0013.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class SwipeCardLayoutManager<T>(@NonNull private val recyclerView: RecyclerView,
                                datas: MutableList<T>,
                                swipeListener: OnSwipeListener<T>)
    : RecyclerView.LayoutManager(), View.OnTouchListener {

    private var itemTouchHelper: ItemTouchHelper? = null

    init {
        if (recyclerView.adapter == null) {
            throw NullPointerException("You must setAdapter before setLayoutManager.")
        }
        val callback = SwipeCardItemTouchHelperCallback<T>(recyclerView.adapter, datas, swipeListener)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper!!.attachToRecyclerView(recyclerView)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val holder = recyclerView.getChildViewHolder(v)
        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
            itemTouchHelper!!.startSwipe(holder)
        }
        return false
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler)
        if (itemCount > SwipeCardConfig.DEFAULT_ITEM_COUNT) {
            var position = SwipeCardConfig.DEFAULT_ITEM_COUNT
            while (position >= 0) {
                val view = recycler!!.getViewForPosition(position)
                addView(view)
                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = width - getDecoratedMeasuredHeight(view)
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view))
                when {
                    position == SwipeCardConfig.DEFAULT_ITEM_COUNT -> {
                        view.scaleX = (1 - (position - 1) * SwipeCardConfig.DEFAULT_SCALE)
                        view.scaleY = (1 - (position - 1) * SwipeCardConfig.DEFAULT_SCALE)
                        view.translationY = (position - 1) * view.measuredHeight / SwipeCardConfig.DEFAULT_TRANSLATE_Y.toFloat()
                    }
                    position > 0 -> {
                        view.scaleX = 1 - position * SwipeCardConfig.DEFAULT_SCALE
                        view.scaleY = 1 - position * SwipeCardConfig.DEFAULT_SCALE
                        view.translationY = position * view.measuredHeight / SwipeCardConfig.DEFAULT_TRANSLATE_Y.toFloat()
                    }
                    else -> view.setOnTouchListener(this)
                }
                position--
            }
        } else {
            var position = itemCount - 1
            while (position >= 0) {
                val view = recycler!!.getViewForPosition(position)
                addView(view)
                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = height - getDecoratedMeasuredHeight(view)
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view))
                if (position > 0) {
                    view.scaleX = 1 - position * SwipeCardConfig.DEFAULT_SCALE
                    view.scaleY = 1 - position * SwipeCardConfig.DEFAULT_SCALE
                    view.translationY = position * view.measuredHeight / SwipeCardConfig.DEFAULT_TRANSLATE_Y.toFloat()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.translationZ = SwipeCardConfig.DEFAULT_ITEM_COUNT - 1 - position * 1f
                    }
                } else {
                    view.setOnTouchListener(this)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.translationZ = SwipeCardConfig.DEFAULT_ITEM_COUNT * 1f
                    }
                }
                position--
            }
        }
    }

}