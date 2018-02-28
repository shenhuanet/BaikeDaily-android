package com.shenhua.baikedaily

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by shenhua on 2018-02-28-0028.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }
}
