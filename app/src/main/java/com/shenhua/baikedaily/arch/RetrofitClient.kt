package com.shenhua.baikedaily.arch

import com.shenhua.baikedaily.bean.Baike
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by shenhua on 2018-02-27-0027.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class RetrofitClient() {

    private val BASE_URL = "https://baike.baidu.com/api/"
    private var retrofit: Retrofit? = null
    private var instance: RetrofitClient? = null
    private var api: Api? = null

    @Synchronized
    fun getInstance(): RetrofitClient {
        if (instance == null) {
            instance = RetrofitClient()
        }
        return instance as RetrofitClient
    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit?.create(Api::class.java)
    }

    fun getBaikeList(page: String, keyword: String): Call<ArrayList<Baike>> {
        val map = HashMap<String, String>()
        map.put("count", "6")
        map.put("page", page)
        map.put("keyWord", keyword)
        return api!!.getList(map)
    }
}