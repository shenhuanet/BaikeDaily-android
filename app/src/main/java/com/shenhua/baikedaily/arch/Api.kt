package com.shenhua.baikedaily.arch

import com.shenhua.baikedaily.bean.Baike
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by shenhua on 2018-02-27-0027.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
interface Api {

    @GET("vbaike/knowledgelist")
    fun getList(@QueryMap param: HashMap<String, String>): Call<ArrayList<Baike>>
}