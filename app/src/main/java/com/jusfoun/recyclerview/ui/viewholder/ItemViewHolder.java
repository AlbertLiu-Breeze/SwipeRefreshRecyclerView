package com.jusfoun.recyclerview.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jusfoun.recyclerview.R;
import com.jusfoun.recyclerview.ui.model.SimpleModel;

/**
 * Created by Albert on 2015/12/23.
 * Mail : lbh@jusfoun.com
 * TODO :
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.avatar);
        mTextView = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public void updateView(SimpleModel model){
        mImageView.setImageResource(R.mipmap.ic_launcher);
        mTextView.setText(model.getName());
    }
    
}
