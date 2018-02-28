package com.shenhua.baikedaily.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shenhua.baikedaily.bean.Baike;

/**
 * Created by shenhua on 2018-02-28-0028.
 *
 * @author shenhua
 *         Email shenhuanet@126.com
 */
@Database(entities = {Baike.class}, version = 1, exportSchema = false)
public abstract class BaikeDatabase extends RoomDatabase {

    public abstract BaikeDao baikeDao();
    private static BaikeDatabase sInstance = null;

    public static BaikeDatabase get(Context context) {
        if (sInstance == null) {
            synchronized (BaikeDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), BaikeDatabase.class, "baike").build();
                }
            }
        }
        return sInstance;
    }

}
