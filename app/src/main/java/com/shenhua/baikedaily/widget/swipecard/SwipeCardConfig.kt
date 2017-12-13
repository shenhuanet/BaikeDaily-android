package com.shenhua.baikedaily.widget.swipecard

/**
 * Created by shenhua on 2017-12-13-0013.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
object SwipeCardConfig {

    /**
     * 卡片总数
     */
    val DEFAULT_ITEM_COUNT = 6
    /**
     * 缩放的比例
     */
    val DEFAULT_SCALE = 0.1f
    /**
     * 卡片 Y 轴偏移量时按照 14 等分计算
     */
    val DEFAULT_TRANSLATE_Y = 14
    /**
     * 卡片滑动时默认倾斜的角度
     */
    val DEFAULT_ROTATE_DEGREE = 15f
    /**
     * 卡片滑动时不偏左也不偏右
     */
    val SWIPING_NONE = 1
    /**
     * 卡片向左滑动时
     */
    val SWIPING_LEFT = 1 shl 2
    /**
     * 卡片向右滑动时
     */
    val SWIPING_RIGHT = 1 shl 3
    /**
     * 卡片从左边滑出
     */
    val SWIPED_LEFT = 1
    /**
     * 卡片从右边滑出
     */
    val SWIPED_RIGHT = 1 shl 2

}