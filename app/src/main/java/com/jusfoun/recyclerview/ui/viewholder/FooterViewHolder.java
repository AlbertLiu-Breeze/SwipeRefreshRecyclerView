package com.jusfoun.recyclerview.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jusfoun.recyclerview.R;
import com.jusfoun.recyclerview.ui.view.MaterialProgressBarSupport;

/**
 * Created by Albert on 2015/12/23.
 * Mail : lbh@jusfoun.com
 * TODO :
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {
    public MaterialProgressBarSupport mProgressView;
    private TextView mTextView;
    public FooterViewHolder(View itemView) {
        super(itemView);
        mProgressView = (MaterialProgressBarSupport) itemView.findViewById(R.id.progress_view);
        mTextView = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public void update(boolean hasMoreData){
        if (hasMoreData){
            mProgressView.setVisibility(View.VISIBLE);
            mProgressView.startProgress();
            mTextView.setText("加载中...");
        }else {
            mProgressView.stopProgress();
            mProgressView.setVisibility(View.GONE);
            mTextView.setText("没有更多数据了");
        }
    }
}
