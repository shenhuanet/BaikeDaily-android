package com.shenhua.baikedaily.arch

import android.arch.lifecycle.LiveData
import com.shenhua.baikedaily.bean.Baike
import com.shenhua.baikedaily.database.BaikeDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import kotlin.concurrent.thread

/**
 * Created by shenhua on 2018-02-28-0028.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class BaikeRepo @Inject constructor(@Named("BaikeDao") dao: BaikeDao) {

    var dao: BaikeDao? = dao

    fun getBaike(): LiveData<ArrayList<Baike>> {
        updateData()
        return dao!!.getAll()
    }

    private fun updateData() {
        thread {
            val r = RetrofitClient.get()!!.getBaikeList("1", "")
            r.enqueue(object : Callback<ArrayList<Baike>> {
                override fun onResponse(call: Call<ArrayList<Baike>>?, response: Response<ArrayList<Baike>>?) {
                    val datas = response?.body()!!
                    dao!!.insertAll(datas)
                }

                override fun onFailure(call: Call<ArrayList<Baike>>?, t: Throwable?) {
                    println(t)
                }
            })
        }
    }

}