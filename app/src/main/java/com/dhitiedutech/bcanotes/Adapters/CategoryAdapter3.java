package com.dhitiedutech.bcanotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhitiedutech.bcanotes.Modals.Category;
import com.dhitiedutech.bcanotes.R;

import java.util.ArrayList;

public class CategoryAdapter3 extends RecyclerView.Adapter<CategoryAdapter3.CategoryHolder> {
    Context context;
    ArrayList<Category> list;


    public CategoryAdapter3(Context context, ArrayList<Category> list) {
        this.context = context;
        this.list = list;
    }
    public CategoryAdapter3(){}

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_sample_list3,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category modal= list.get(position);
        holder.imgView.setImageResource(modal.getImgView());
        holder.tvName.setText(modal.getTvName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView tvName;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            imgView=itemView.findViewById(R.id.imgGame);
            tvName=itemView.findViewById(R.id.txtGame);

        }
    }
}
