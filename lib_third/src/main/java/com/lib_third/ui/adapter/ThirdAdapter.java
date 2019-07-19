package com.lib_third.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baselibrary.Constant;
import com.lib_third.R;

import java.util.List;


/**
 * Created by 简言 on 2019/6/17  11:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_second1.ui.adapter
 * Description :
 */
public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.HomeViewHolder> {

    private Context context;
    private List<String> secondPageTables;

    public ThirdAdapter(Context context, List<String> secondPageTables){

        this.context = context;
        this.secondPageTables = secondPageTables;
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_third, viewGroup, false);
        return new HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {

        homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build(Constant.TEXT_ACTIVITY).navigation();
            }
        });
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemIm;
        private TextView itemTitle;
        private TextView itemPrice;
        private TextView itemPayed;
        private View itemView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
