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
        RelativeLayout itemLayout;

        public ViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.list_image);
            itemName = (TextView) view.findViewById(R.id.list_name);
            itemDetail = (LinearLayout) view.findViewById(R.id.item_detail);
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
        holder.itemImage.setImageResource(item.getImageId());
        holder.bindView(position,mItemList.get(position));
    }

    public int getItemCount(){
        return mItemList.size();
    }
}
