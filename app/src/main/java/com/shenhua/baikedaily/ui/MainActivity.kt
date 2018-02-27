package com.shenhua.baikedaily.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.View
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.arch.RetrofitClient
import com.shenhua.baikedaily.bean.Baike
import com.shenhua.baikedaily.ui.adapter.BaseRecyclerAdapter
import com.shenhua.baikedaily.ui.adapter.CardAdapter
import com.shenhua.baikedaily.widget.swipecard.OnSwipeListener
import com.shenhua.baikedaily.widget.swipecard.SwipeCardLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        thread {
            val r = RetrofitClient().getInstance().getBaikeList("1", "")
            r.enqueue(object : Callback<ArrayList<Baike>> {
                override fun onResponse(call: Call<ArrayList<Baike>>?, response: Response<ArrayList<Baike>>?) {
                    datas = response?.body()!!
                    recyclerView.layoutManager = SwipeCardLayoutManager(recyclerView, datas, this@MainActivity)
                    adapter!!.datas = datas
                    loading.stop()
                }

                override fun onFailure(call: Call<ArrayList<Baike>>?, t: Throwable?) {
                    println(t)
                    loading.stop()
                }
            })
        }
    }
}
