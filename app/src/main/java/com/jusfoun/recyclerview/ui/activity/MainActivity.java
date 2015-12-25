package com.jusfoun.recyclerview.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.jusfoun.recyclerview.R;
import com.jusfoun.recyclerview.ui.adapter.ScaleInAnimationAdapter;
import com.jusfoun.recyclerview.ui.adapter.SimpleAdapter;
import com.jusfoun.recyclerview.ui.animation.ScaleInLeftAnimator;
import com.jusfoun.recyclerview.ui.model.SimpleModel;
import com.jusfoun.recyclerview.ui.widget.ReadyRecyclerView;
import com.jusfoun.recyclerview.util.Constant;


public class MainActivity extends ActionBarActivity{

    private TextView mAddOne,mDeleteOne,mAddGroup,mDeleteGroup;
    private ReadyRecyclerView mReadyRecyclerView;

    private SimpleAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initWidgetAction();
    }

    private void initData(){
        mLinearLayoutManager = new LinearLayoutManager(this);
        //mLinearLayoutManager =  new GridLayoutManager(this, 3);
        mAdapter = new SimpleAdapter(this);
    }

    private void initView(){
        mAddOne = (TextView) findViewById(R.id.add_one);
        mDeleteOne = (TextView) findViewById(R.id.delete_one);
        mAddGroup = (TextView) findViewById(R.id.add_group);
        mDeleteGroup = (TextView) findViewById(R.id.delete_group);
        mReadyRecyclerView = (ReadyRecyclerView) findViewById(R.id.ready_recyclerview);
    }

    private void initWidgetAction(){
        mAdapter.setHasMoreData(true);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(mAdapter);
        //mReadyRecyclerView.setAdapter(scaleAdapter);
        mReadyRecyclerView.setAdapter(mAdapter);
        mReadyRecyclerView.setItemAnimatior(new ScaleInLeftAnimator(new OvershootInterpolator(1f)));


        /*((GridLayoutManager)mLinearLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //加载更多 占领 整个一行
                switch (mAdapter.getItemViewType(position)) {
                    case SimpleAdapter.FOOTER_VIEW_TYPE:
                        return 3;//number of columns of the grid
                    case SimpleAdapter.ITEM_VIEW_TYPE:
                        return 1;
                    default:
                        return -1;
                }
            }

        });*/
        mReadyRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleModel model = new SimpleModel();
                model.setLayoutType(Constant.ITEM_LAYOUT_TYPE);
                model.setName("This is item " + mAdapter.getItemCount());
                mAdapter.addOne(model);
            }
        });

        mDeleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mDeleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mReadyRecyclerView.setEnableRefresh(true);
        mReadyRecyclerView.setEnableLoadMore(true);


        mReadyRecyclerView.setRecyclerViewListener(new ReadyRecyclerView.ReadyRecyclerViewListener() {
            @Override
            public void onRefreshData() {
                Log.e("tag", "onRefreshData");
                mReadyRecyclerView.setIsRefreshing(true);
                SimpleModel model = new SimpleModel();
                model.setLayoutType(Constant.ITEM_LAYOUT_TYPE);
                model.setName("This is item " + mAdapter.getItemCount());
                mAdapter.addOne(model);
                mReadyRecyclerView.setIsRefreshing(false);

            }

            @Override
            public void onLoadMoreData() {
                Log.e("tag", "onLoadMoreData");
                mAdapter.setHasFooter(true);
                mReadyRecyclerView.setIsLoadingMore(true);
                for (int i = 0; i < 5; i++){
                    SimpleModel model = new SimpleModel();
                    model.setLayoutType(Constant.ITEM_LAYOUT_TYPE);
                    model.setName("This is item " + mAdapter.getItemCount());
                    mAdapter.addOne(model);
                }


                SimpleModel model1 = new SimpleModel();
                model1.setLayoutType(Constant.FOOTER_LAYOUT_TYPE);
                mAdapter.addOne(model1);

                mReadyRecyclerView.setIsLoadingMore(false);

            }
        });

    }




}
