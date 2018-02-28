package com.shenhua.baikedaily.module;

import com.shenhua.baikedaily.ui.MainActivity;

import dagger.Component;

/**
 * Created by shenhua on 2018-02-28-0028.
 *
 * @author shenhua
 *         Email shenhuanet@126.com
 */
@Component(modules = BaikeModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
