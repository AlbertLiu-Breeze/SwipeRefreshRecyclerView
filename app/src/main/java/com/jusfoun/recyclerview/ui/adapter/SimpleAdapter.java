package com.jusfoun.recyclerview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jusfoun.recyclerview.R;
import com.jusfoun.recyclerview.ui.model.SimpleModel;
import com.jusfoun.recyclerview.ui.viewholder.FooterViewHolder;
import com.jusfoun.recyclerview.ui.viewholder.ItemViewHolder;
import com.jusfoun.recyclerview.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert on 2015/12/23.
 * Mail : lbh@jusfoun.com
 * TODO :
 *
 */
public class SimpleAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SimpleModel> mDataList;
    private boolean hasMoreData,hasFooter;

    public final static int HEADER_VIEW_TYPE = 0;
    public final static int ITEM_VIEW_TYPE = 1;
    public final static int FOOTER_VIEW_TYPE = 2;

    public SimpleAdapter(Context mContext) {
        this.mContext = mContext;
        mDataList = new ArrayList<>();
        hasMoreData = false;
        hasFooter = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_VIEW_TYPE){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }else if (viewType == FOOTER_VIEW_TYPE){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_view_load_more, viewGroup, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder){
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.updateView(mDataList.get(position));
        }else if (viewHolder instanceof FooterViewHolder){
            FooterViewHolder holder = (FooterViewHolder) viewHolder;
            holder.update(hasMoreData);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for(SimpleModel model : mDataList){
            if (model.getLayoutType() == Constant.ITEM_LAYOUT_TYPE){
                itemCount ++ ;
            }
        }
       // return itemCount;
        return mDataList.size();
    }



    @Override
    public int getItemViewType(int position) {
        SimpleModel model = mDataList.get(position);
        if (model.getLayoutType() == Constant.ITEM_LAYOUT_TYPE){
            return ITEM_VIEW_TYPE;
        }else if (model.getLayoutType() == Constant.FOOTER_LAYOUT_TYPE){
            return FOOTER_VIEW_TYPE;
        }

        return -1;

    }

    public void refresh(List<SimpleModel> data){
        mDataList.clear();
        mDataList.addAll(data);
        notifyItemRangeChanged(0, mDataList.size());
    }

    public void addMore(List<SimpleModel> data){
        int start = mDataList.size();
        mDataList.addAll(data);
        notifyItemRangeInserted(start, data.size());
    }

    public void addOne(SimpleModel model){
        if (mDataList.size() > 0){
            if (mDataList.get(mDataList.size() - 1) != null){
                SimpleModel model1 = mDataList.get(mDataList.size() - 1);
                if (model1.getLayoutType() == Constant.FOOTER_LAYOUT_TYPE){
                    mDataList.remove(mDataList.size() - 1);
                    notifyItemRemoved(mDataList.size() - 1);
                }

            }
        }
        mDataList.add(model);
        notifyItemInserted(mDataList.size() - 1);
    }

    public void addOne(SimpleModel model,int position){
        mDataList.add(model);
        notifyItemInserted(position);
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
        notifyDataSetChanged();
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
        notifyDataSetChanged();
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }
}
