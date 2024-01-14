package com.dhitiedutech.bcanotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhitiedutech.bcanotes.Modals.PdfListModel;
import com.dhitiedutech.bcanotes.R;


import java.util.ArrayList;

public class PdfListAdapter extends RecyclerView.Adapter<PdfListAdapter.ViewHolder> {
    ArrayList<PdfListModel> list;
    Context context;
    public PdfListAdapter (ArrayList<PdfListModel> list , Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_resorce_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PdfListModel model = list.get(position);
        holder.tvI.setText(model.getI().toString());
        holder.imageView.setImageResource(model.getImgId());
        holder.tvFileName.setText(model.getFileName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvFileName,tvI;
        public ViewHolder (@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvFileName = itemView.findViewById(R.id.tvFileName);
            tvI = itemView.findViewById(R.id.tvI);
        }
    }
}
