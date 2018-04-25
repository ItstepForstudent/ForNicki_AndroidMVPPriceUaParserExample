package com.step.lifehuck.utils.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.step.lifehuck.R;
import com.step.lifehuck.entities.Good;
import com.step.lifehuck.entities.LifeHuck;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by mamedov on 02.04.2018.
 */

public class LifeHuckRVAdapter extends RecyclerView.Adapter<LifeHuckRVAdapter.ViewHolder> {
    List<Good> goods = new ArrayList<>();
    PublishSubject<String> onItemClicker = PublishSubject.create();
    Context ctx;
    public void setGoods(List<Good> goods){
        this.goods = goods;
        this.notifyDataSetChanged();
    }

    public Observable<String> onItemClick(){
        return onItemClicker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(goods.get(position));
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nameText;
        TextView viewsText;
        TextView timeText;
        TextView viewstwo;
        TextView firma;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nameText = itemView.findViewById(R.id.nameText);
            viewsText = itemView.findViewById(R.id.views);
            timeText =itemView.findViewById(R.id.timeText);
            viewstwo =itemView.findViewById(R.id.viewstwo);
            firma =itemView.findViewById(R.id.firma);

        }
        public void bind(Good good){
            itemView.setOnClickListener(v->onItemClicker.onNext(good.getName()));
            nameText.setText(good.getName());
            timeText.setText(good.getTime());
            viewsText.setText(String.valueOf(good.getPrice()));
            firma.setText(good.getFirna());
            viewstwo.setText(String.valueOf(good.getPracetwo()));
            Picasso.with(ctx).load(good.getImgSrc()).into(image);
        }
    }
}
