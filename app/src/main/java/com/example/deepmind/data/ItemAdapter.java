package com.example.deepmind.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.deepmind.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<DataItem> mItemList;
    private int opened = -1;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
        TextView itemName;
        LinearLayout itemDetail;
        TextView itemScore;
        TextView itemStar1;
        TextView itemStar2;
        TextView itemStar3;

        RelativeLayout itemLayout;

        public ViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.list_image);
            itemName = (TextView) view.findViewById(R.id.list_name);
            itemDetail = (LinearLayout) view.findViewById(R.id.item_detail);
            itemScore = (TextView) view.findViewById(R.id.item_score);
            itemStar1 = (TextView) view.findViewById(R.id.item_star1);
            itemStar2 = (TextView) view.findViewById(R.id.item_star2);
            itemStar3 = (TextView) view.findViewById(R.id.item_star3);

            itemLayout = (RelativeLayout) view.findViewById(R.id.item_layout);
            itemLayout.setOnClickListener(this);
        }

        void bindView(int pos, DataItem item) {
            if (pos == opened){
                itemDetail.setVisibility(View.VISIBLE);
            } else{
                itemDetail.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View view) {
            if (opened == getAdapterPosition()) {
                opened = -1;
                notifyItemChanged(getAdapterPosition());
            } else {
                int oldOpened = opened;
                opened = getAdapterPosition();
                notifyItemChanged(oldOpened);
                notifyItemChanged(opened);
            }
        }
    }

    public ItemAdapter(List<DataItem> itemList){
        mItemList = itemList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        DataItem item = mItemList.get(position);
        holder.itemName.setText(item.getName());
        if(item.getId() == 1){
            holder.itemImage.setImageResource(R.drawable.tick);
        }else{
            holder.itemImage.setImageResource(R.drawable.cross);
        }
        holder.itemScore.setText("该顾客对本商家打分为：" + item.getScore());
        holder.itemStar1.setText("口味：" + item.getStar1());
        holder.itemStar2.setText("环境：" + item.getStar2());
        holder.itemStar3.setText("食物：" + item.getStar3());

        holder.bindView(position,mItemList.get(position));
    }

    public int getItemCount(){
        return mItemList.size();
    }
}
