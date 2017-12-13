package com.shenhua.baikedaily.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.ui.adapter.CardAdapter
import com.shenhua.baikedaily.widget.swipecard.OnSwipeListener
import com.shenhua.baikedaily.widget.swipecard.SwipeCardConfig
import com.shenhua.baikedaily.widget.swipecard.SwipeCardLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSwipeListener<String> {

    override fun onSwiping(viewHolder: RecyclerView.ViewHolder, ratio: Float, direction: Int) {
        println("onSwiping")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, t: String, direction: Int) {
        println("onSwiped")
    }

    override fun onSwipedClear() {
        println("onSwipedClear")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val datas = ArrayList<String>()
        (0 until SwipeCardConfig.DEFAULT_ITEM_COUNT).mapTo(datas) { "i:$it" }
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = CardAdapter(this, datas)
        recyclerView.layoutManager = SwipeCardLayoutManager(recyclerView, datas, this)
    }

}
