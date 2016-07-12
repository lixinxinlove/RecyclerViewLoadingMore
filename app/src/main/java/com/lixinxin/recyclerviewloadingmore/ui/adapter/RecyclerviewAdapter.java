package com.lixinxin.recyclerviewloadingmore.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixinxin.recyclerviewloadingmore.R;

import java.util.List;

/**
 * Created by lixinxin on 2016/7/12.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private static final int TYPE_NOMAL = 1;
    private static final int TYPE_MORE = 2;
    public boolean mIsFooterEnable = false;   //是否显示加载更多
    private Context context;
    public List<String> lists;
    private View footView;


    public RecyclerviewAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
        footView = View.inflate(context, R.layout.foot_more_view, null);
        footView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_normal, null);
        if (viewType == TYPE_MORE) {
            return new ViewHolder(footView);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MORE) {
            return;
        }
        holder.tv.setText(lists.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        if (position == lists.size() && mIsFooterEnable) {
            return TYPE_MORE;
        }
        return TYPE_NOMAL;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (lists != null) {
            count = lists.size();
        } else {
            return 0;
        }
        if (mIsFooterEnable) {
            count++;
        }
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
