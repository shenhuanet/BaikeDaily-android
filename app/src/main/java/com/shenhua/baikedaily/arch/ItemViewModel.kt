package com.shenhua.baikedaily.arch

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.shenhua.baikedaily.bean.Item

/**
 * Created by shenhua on 2017-12-14-0014.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class ItemViewModel : ViewModel() {

    var items: MutableLiveData<ArrayList<Item>>? = null

//    fun getItems(): LiveData<ArrayList<Item>> {
//        if (items == null) {
//            items = MutableLiveData()
//        }
//        return items
//    }

}