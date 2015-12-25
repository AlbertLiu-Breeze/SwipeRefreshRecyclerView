package com.jusfoun.recyclerview.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.jusfoun.recyclerview.R;

/**
 * Created by Albert on 2015/12/24.
 * Mail : lbh@jusfoun.com
 * TODO :
 */
public class ReadyRecyclerView extends RelativeLayout {
    private Context mContext;
    private CustomSwipeRefreshLayout mSwipeRefresh;
    private CustomRecyclerView mRecyclerView;

    private boolean enableRefresh,enableLoadMore,isRefreshing,isLoadingMore;

    private ReadyRecyclerViewListener mListener;
    private RecyclerView.LayoutManager mLayoutManager;

    public ReadyRecyclerView(Context context) {
        super(context);
        initData(context);
        initView();
        initWidgetAction();
    }

    public ReadyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
        initView();
        initWidgetAction();
    }

    public ReadyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
        initView();
        initWidgetAction();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReadyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData(context);
        initView();
        initWidgetAction();
    }

    private void initData(Context context){
        mContext = context;
        enableRefresh = false;
        enableLoadMore = false;
        isRefreshing = false;
        isLoadingMore = false;
    }

    private void initView(){
        LayoutInflater.from(mContext).inflate(R.layout.ready_recyclerview_layout, this, true);
        mSwipeRefresh = (CustomSwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mRecyclerView = (CustomRecyclerView) findViewById(R.id.recycler_view);
    }

    private void initWidgetAction(){
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mListener != null){
                    mListener.onRefreshData();
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mRecyclerView.isPullUp() && !mRecyclerView.isLoadingMore() && mRecyclerView.isEnableLoadMore()) {
                    int lastPos = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                    if (mRecyclerView.getAdapter() != null) {
                        if (lastPos > mRecyclerView.getAdapter().getItemCount() - 2) {//最后一个位置的时候加载更多
                            mRecyclerView.setIsLoadingMore(true);
                            mListener.onLoadMoreData();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mRecyclerView.setIsPullUp(dy > 0);
            }
        });

        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {

            }
        });
    }

    public void setAdapter(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
        mRecyclerView.setLayoutManager(mLayoutManager);
        this.mLayoutManager = mLayoutManager;
    }

    public void setItemAnimatior(RecyclerView.ItemAnimator animator){

        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);
    }

    public boolean isEnableRefresh() {
        return enableRefresh;
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        mSwipeRefresh.setEnableRefresh(enableRefresh);
    }

    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        mRecyclerView.setEnableLoadMore(enableLoadMore);
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setIsRefreshing(boolean isRefreshing) {
        this.isRefreshing = isRefreshing;
        mSwipeRefresh.setRefreshing(isRefreshing);
    }

    public boolean isLoadingMore() {
        return isLoadingMore;
    }

    public void setIsLoadingMore(boolean isLoadingMore) {
        this.isLoadingMore = isLoadingMore;
        mRecyclerView.setIsLoadingMore(isLoadingMore);
    }

    public interface ReadyRecyclerViewListener{
        public void onRefreshData();
        public void onLoadMoreData();
    }

    public void setRecyclerViewListener(ReadyRecyclerViewListener mListener) {
        this.mListener = mListener;
    }
}
