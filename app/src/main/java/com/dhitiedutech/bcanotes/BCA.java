package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dhitiedutech.bcanotes.Adapters.CategoryAdapter;
import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.Category;
import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.databinding.ActivityBcaBinding;

import java.util.ArrayList;

public class BCA extends AppCompatActivity {
    public  static final String MSG="com.example.mynotes.BCA";
    ActivityBcaBinding binding;
    ImageView btnSyllabus,btnNotes,btnPaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBcaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        navigating to syllabus,notes,paper activities
//        btnSyllabus=findViewById(R.id.btnSyllabus);
//        btnSyllabus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentSyllabus=new Intent(BCA.this,DocumentViewer.class );
//                intentSyllabus.putExtra("MSG","bca.pdf");
//                startActivity(intentSyllabus);
//            }
//        });
//        btnNotes=findViewById(R.id.btnNotes);
//        btnNotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentNotes =  new Intent(BCA.this,SelectSem.class);
//                intentNotes.putExtra(MSG,"bcaNotes");
//                CourseDetails.NOTES=1;
//                startActivity(intentNotes);
//            }
//        });
//        btnPaper=findViewById(R.id.btnPaper);
//        btnPaper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentPaper=new Intent(BCA.this,SelectSem.class);
//                intentPaper.putExtra(MSG,"bcaPaper");
//                CourseDetails.PAPER=1;
//                startActivity(intentPaper);
//            }
//        });
        ArrayList<Category> list=new ArrayList<>();
        list.add(new Category(R.drawable.syllabus,"Syllabus"));
        list.add(new Category(R.drawable.paper,"Papers"));
        list.add(new Category(R.drawable.notes,"Hand Written Notes"));
        list.add(new Category(R.drawable.paper_,"BCA Notes (All university)"));

        CategoryAdapter adapter=new CategoryAdapter(this,list);
        binding.recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(BCA.this  ,2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(BCA.this, binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0){
                    Intent intentSyllabus=new Intent(BCA.this,DocumentViewer.class );
                    intentSyllabus.putExtra("MSG","bca.pdf");
                    startActivity(intentSyllabus);
                }else if(position==1){
                    Intent intentPaper=new Intent(BCA.this,SelectSem.class);
                    intentPaper.putExtra(MSG,"bcaPaper");
                    CourseDetails.PAPER=1;
                    startActivity(intentPaper);
                }else if(position==2){
                    Intent intentNotes =  new Intent(BCA.this,SelectSem.class);
                    intentNotes.putExtra(MSG,"bcaNotes");
                    CourseDetails.NOTES=1;
                    startActivity(intentNotes);
                }else if(position==3){
                    startActivity(new Intent(BCA.this,Notes.class));
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //Reseting the variable to navigate correctly
        CourseDetails.COURSE=0;
    }
}