package com.shenhua.baikedaily.arch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.shenhua.baikedaily.bean.Baike
import javax.inject.Inject

/**
 * Created by shenhua on 2017-12-14-0014.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class BaikeViewModel @Inject constructor(repo: BaikeRepo) : ViewModel() {

    private var repo: BaikeRepo? = repo

    fun getItems(): LiveData<ArrayList<Baike>> {
        return repo?.getBaike()!!
    }
}