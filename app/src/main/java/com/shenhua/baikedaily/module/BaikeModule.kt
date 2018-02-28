package com.shenhua.baikedaily.module

import com.shenhua.baikedaily.App
import com.shenhua.baikedaily.database.BaikeDao
import com.shenhua.baikedaily.database.BaikeDao_Impl
import com.shenhua.baikedaily.database.BaikeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by shenhua on 2018-02-28-0028.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
@Module
class BaikeModule {

    @Provides
    @Named("BaikeDao")
    fun provideBaikeDao(): BaikeDao {
        return BaikeDao_Impl(BaikeDatabase.get(App.context))
    }
}