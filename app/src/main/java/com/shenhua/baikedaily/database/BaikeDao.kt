package com.shenhua.baikedaily.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.shenhua.baikedaily.bean.Baike
import java.util.*

/**
 * Created by shenhua on 2018-02-28-0028.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
@Dao
interface BaikeDao {

    @Query("SELECT * FROM baike")
    fun getAll(): LiveData<ArrayList<Baike>>

    @Insert(onConflict = REPLACE)
    fun save(baike: Baike)

    @Insert
    fun insertAll(list: ArrayList<Baike>)

    @Delete
    fun delete(baike: Baike)
}
