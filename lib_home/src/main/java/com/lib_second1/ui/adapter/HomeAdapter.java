package com.lib_second1.ui.adapter;

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
import com.lib_second1.R;
import com.lib_second1.bean.HomePageTable;

import java.util.List;

/**
 * Created by 简言 on 2019/6/17  11:47.
 * 努力吧 ！ 少年 ！
 * email : yifeng20161123@163.com
 *
 * @package : com.lib_second1.ui.adapter
 * Description :
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context context;
    private List<HomePageTable> homePageTables;

    public HomeAdapter(Context context, List<HomePageTable> homePageTables){

        this.context = context;
        this.homePageTables = homePageTables;
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home, viewGroup, false);
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
            itemIm = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemPayed = itemView.findViewById(R.id.item_payed);
        }
    }
}
