package com.shenhua.baikedaily.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.View
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.arch.BaikeViewModel
import com.shenhua.baikedaily.bean.Baike
import com.shenhua.baikedaily.ui.adapter.BaseRecyclerAdapter
import com.shenhua.baikedaily.ui.adapter.CardAdapter
import com.shenhua.baikedaily.widget.swipecard.OnSwipeListener
import com.shenhua.baikedaily.widget.swipecard.SwipeCardLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnSwipeListener<Baike> {

    override fun onSwiping(viewHolder: RecyclerView.ViewHolder, ratio: Float, direction: Int) {
        println("onSwiping")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, t: Baike, direction: Int) {
        println("onSwiped")
    }

    override fun onSwipedClear() {
        println("onSwipedClear")
    }

    private var datas: ArrayList<Baike> = ArrayList<Baike>()
    private var adapter: CardAdapter? = null
    @Inject
    private var viewModel: BaikeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.create().inject(this)

        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = CardAdapter(this, datas)
        recyclerView.adapter = adapter
        adapter?.setOnItemClickListener(object : BaseRecyclerAdapter.OnItemClickListener<Baike> {

            override fun onItemClick(view: View, position: Int, data: Baike) {
                startActivity<ContentActivity>("link" to data.link, "title" to data.title)
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getData()
    }

    private fun getData() {
        loading.start()
        viewModel!!.getItems().observe(this, Observer<ArrayList<Baike>> {
            recyclerView.layoutManager = SwipeCardLayoutManager(recyclerView, datas, this@MainActivity)
            adapter!!.datas = datas
            loading.stop()
        })
    }
}
