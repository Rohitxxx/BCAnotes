package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Adapters.CategoryAdapter3;
import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.Category;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.WebScrapingNotes.NotesByWebScraping;
import com.dhitiedutech.bcanotes.databinding.ActivityNotesBinding;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    ActivityNotesBinding binding;
    String notesUrl[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRecyclerView();
        initilizeUlrs();
    }
    public void setRecyclerView(){
        ArrayList<Category> list=new ArrayList<>();
        list.add(new Category(R.drawable.computer_funda,"Computer Fundamentals"));
        list.add(new Category(R.drawable.c,"C"));
        list.add(new Category(R.drawable.digital_electronics,"Digital Electronics"));
        list.add(new Category(R.drawable.os,"OS"));
        list.add(new Category(R.drawable.ob,"Organisational Behaviour"));
        list.add(new Category(R.drawable.c_plus_plus,"C++"));
        list.add(new Category(R.drawable.database,"DBMS"));
        list.add(new Category(R.drawable.engineers,"Software Engineering"));
        list.add(new Category(R.drawable.oops,"Object-Oriented Programming"));
        list.add(new Category(R.drawable.java,"Java"));
        list.add(new Category(R.drawable.conversation,"Communication"));
        list.add(new Category(R.drawable.web_design,"Web Design"));
        list.add(new Category(R.drawable.linux,"Linux"));
        list.add(new Category(R.drawable.it_security,"Information Security"));
        list.add(new Category(R.drawable.python,"Python"));
        CategoryAdapter3 adapter3=new CategoryAdapter3(this,list);
        binding.notesRecyclerView.setAdapter(adapter3);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        binding.notesRecyclerView.setLayoutManager(gridLayoutManager);
        binding.notesRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Notes.this, binding.notesRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(Notes.this, Integer.valueOf(position).toString(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Notes.this, NotesByWebScraping.class);
                intent.putExtra("url",notesUrl[position]);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
    public void initilizeUlrs(){
        notesUrl=new String[]{
                "https://www.tutorialspoint.com/computer_fundamentals",
                "https://www.tutorialspoint.com/cprogramming",
                "https://www.tutorialspoint.com/digital_circuits",
                "https://www.tutorialspoint.com/operating_system",
                "https://www.tutorialspoint.com/organizational_behavior",
                "https://www.tutorialspoint.com/cplusplus",
                "https://www.tutorialspoint.com/dbms",
                "https://www.tutorialspoint.com/software_engineering",
                "https://www.tutorialspoint.com/human_computer_interface",
                "https://www.tutorialspoint.com/java",
                "https://www.tutorialspoint.com/business_communication_strategies",
                "https://www.tutorialspoint.com/internet_technologies",
                "https://www.tutorialspoint.com/unix",
                "https://www.tutorialspoint.com/internet_technologies",
                "https://www.tutorialspoint.com/python"
        };
    }
}