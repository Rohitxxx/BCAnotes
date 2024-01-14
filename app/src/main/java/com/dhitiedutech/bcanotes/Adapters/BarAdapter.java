package com.dhitiedutech.bcanotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhitiedutech.bcanotes.Modals.BarModel;
import com.dhitiedutech.bcanotes.R;

import java.util.ArrayList;

public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarHolder> {
    Context context;
    ArrayList<BarModel> list;

    public BarAdapter(Context context, ArrayList<BarModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bar_data_sample,parent,false);
        return new BarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarHolder holder, int position) {
        BarModel model=list.get(position);
        holder.imageView.setImageResource(model.getImgId());
        holder.tvFileName.setText(model.getFileName());
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
            tvI=itemView.findViewById(R.id.tvI);
            tvFileName=itemView.findViewById(R.id.tvFileName);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
