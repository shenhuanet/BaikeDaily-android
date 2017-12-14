package com.shenhua.baikedaily.widget.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shenhua on 2017-12-14-0014.
 *
 * @author shenhua
 *         Email shenhuanet@126.com
 */
public class Loading extends View {

    public Loading(Context context) {
        this(context, null);
    }

    public Loading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
