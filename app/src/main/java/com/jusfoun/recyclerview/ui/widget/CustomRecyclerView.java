package com.jusfoun.recyclerview.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Albert on 2015/12/23.
 * Mail : lbh@jusfoun.com
 * TODO :
 */
public class CustomRecyclerView extends RecyclerView {
    private boolean isPullUp,isLoadingMore;

    //是否使能上拉加载
    private boolean enableLoadMore;
    public CustomRecyclerView(Context context) {
        super(context);
        initStatus();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStatus();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initStatus();
    }

    private void initStatus(){
        isPullUp = false;
        isLoadingMore = false;
        enableLoadMore = false;
    }

    public boolean isPullUp() {
        return isPullUp;
    }

    public void setIsPullUp(boolean isPullUp) {
        this.isPullUp = isPullUp;
    }


    public boolean isLoadingMore() {
        return isLoadingMore;
    }

    public void setIsLoadingMore(boolean isLoadingMore) {
        this.isLoadingMore = isLoadingMore;
    }

    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
    }
}
