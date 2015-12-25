package com.jusfoun.recyclerview.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jusfoun.recyclerview.R;

/**
 * Created by Albert on 2015/12/23.
 * Mail : lbh@jusfoun.com
 * TODO :
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    //是否使能下拉刷新
    private boolean enableRefresh;

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
        initStatus();
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStatus();
    }

    private void initStatus(){
        enableRefresh = false;
        setColorSchemeResources(R.color.line_color_run_speed_13);
    }

    public boolean isEnableRefresh() {
        return enableRefresh;
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        setEnabled(enableRefresh);
    }

}
