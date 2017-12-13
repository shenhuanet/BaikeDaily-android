package com.shenhua.baikedaily.widget.swipecard

import android.support.v7.widget.RecyclerView


/**
 * Created by shenhua on 2017-12-13-0013.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
interface OnSwipeListener<in T> {
    /**
     * 卡片还在滑动时回调
     *
     * @param viewHolder 该滑动卡片的 viewHolder
     * @param ratio      滑动进度的比例
     * @param direction  卡片滑动的方向，CardConfig.SWIPING_LEFT 为向左滑，CardConfig.SWIPING_RIGHT 为向右滑，
     * CardConfig.SWIPING_NONE 为不偏左也不偏右
     */
    fun onSwiping(viewHolder: RecyclerView.ViewHolder, ratio: Float, direction: Int)

    /**
     * 卡片完全滑出时回调
     *
     * @param viewHolder 该滑出卡片的 viewHolder
     * @param t          该滑出卡片的数据
     * @param direction  卡片滑出的方向，CardConfig.SWIPED_LEFT 为左边滑出；CardConfig.SWIPED_RIGHT 为右边滑出
     */
    fun onSwiped(viewHolder: RecyclerView.ViewHolder, t: T, direction: Int)

    /**
     * 所有的卡片全部滑出时回调
     */
    fun onSwipedClear()
}