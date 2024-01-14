package com.dhitiedutech.bcanotes.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhitiedutech.bcanotes.Modals.WebData;
import com.dhitiedutech.bcanotes.R;

import java.util.ArrayList;

public class BarAdapter2 extends RecyclerView.Adapter<BarAdapter2.BarHolder> {
    Context context;
    ArrayList<WebData> list;

    public BarAdapter2(Context context, ArrayList<WebData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bar_data_sample_scraping,parent,false);
        return new BarHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BarHolder holder, int position) {
        WebData model=list.get(position);
        holder.imageView.setImageResource(model.getImg());
        holder.tvFileName.setText(model.getHtmlContent());
        holder.tvI.setText(model.getI().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BarHolder extends RecyclerView.ViewHolder{
        TextView tvI,tvFileName;
        ImageView imageView;

        public BarHolder(@NonNull View itemView) {
            super(itemView);
            tvI=itemView.findViewById(R.id.tvIScraping);
            tvFileName=itemView.findViewById(R.id.tvFileNameScraping);
            imageView=itemView.findViewById(R.id.imageViewScraping);
        }
    }
}
