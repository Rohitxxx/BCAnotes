package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.Adapters.PdfListAdapter;
import com.dhitiedutech.bcanotes.Modals.PdfListModel;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.databinding.ActivitySubjectListBinding;

import java.util.ArrayList;

public class SubjectList extends AppCompatActivity {


    String [][]subjectlist;
    ActivitySubjectListBinding binding;
    public SubjectList(){
        subjectlist= new String[6][];
        subjectlist[0]=new String[]{
                "Computer Fundamental & Office Automation",
                "Programming Principle & Algorithm",
                "Principle of Management",
                "Business Communication",
                "Mathematics-I"
        };
        subjectlist[1] =new String[]{
                "C Programming",
                "Digital Electronics & Computer Organization",
                "Organization Behaviour",
                "Financial Accounting & Management",
                "Mathematics-II"
        };
        subjectlist[2]= new String[]{
                "Object Oriented Programming Using C++",
                "Data Structure Using C & C++",
                "Computer Architecture & Assembly Language",
                "Business Economics",
                "Elements of Statistics"
        };
        subjectlist[3] = new String[]{
                "Computer Graphics & Multimedia Application",
                "Operating System",
                "Software Engineering",
                "Optimization Techniques",
                "Mathematics-III"
        };
        subjectlist[4] = new String[]{
                "Introduction to DBMS",
                "Java Programming & Dynamic Webpage Desing",
                "Computer Network",
                "Numerical Methods"
        };
        subjectlist[5] = new String[]{
                "Computer Network Security",
                "Information System:Analysis Design & Implementation",
                "E-Commerce",
                "Knowledge Management"
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        if(CourseDetails.SEM>0){

            try{
                ArrayList<PdfListModel> list =new ArrayList<>();
                // Setting subject list by semester from Static variable SEM set by previous Activity to list
                for(int i = 0; i<subjectlist[CourseDetails.SEM -1].length; i++){

                    list.add(new PdfListModel(i+1, R.drawable.notes,subjectlist[CourseDetails.SEM-1][i]));
                }
                PdfListAdapter adapter = new PdfListAdapter(list,this);
                binding.recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                binding.recyclerView.setLayoutManager(linearLayoutManager);
               // binding.recyclerView.setLayoutManager(linearLayoutManager);
                binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                        this, binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        CourseDetails.SUBJECT=Integer.valueOf(position);

//                        Toast.makeText(SubjectList.this, position, Toast.LENGTH_SHORT).show();
                        try{
                            if(CourseDetails.NOTES>0){
                                CourseDetails.SUBJECT=Integer.valueOf(position)+1;
                                Intent intent =new Intent(SubjectList.this,ChapterList.class);
                                startActivity(intent);
                            }
                            else if(CourseDetails.PAPER>0){
                                CourseDetails.SUBJECT=Integer.valueOf(position)+1;
                                Intent intent =new Intent(SubjectList.this,PaperList.class);
                                startActivity(intent);
                            }
                        }catch (Exception e){
                            Log.d("error",e.getMessage());
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }
                ));

            }catch (Exception e){
                Toast.makeText(SubjectList.this, "something went wrong please try again", Toast.LENGTH_SHORT).show();
                Log.d("error",e.getMessage());
            }

        }

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //Reseting the variable to navigate correctly
        CourseDetails.SEM=0;
//        Toast.makeText(SubjectList.this, "Back button pressed", Toast.LENGTH_SHORT).show();
    }
}