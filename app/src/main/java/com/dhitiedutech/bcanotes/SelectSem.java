package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.databinding.ActivitySelectSemBinding;

public class SelectSem extends AppCompatActivity {
    ActivitySelectSemBinding binding;
    public  static final String MSG="com.example.mynotes.SelectSem";
    public String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySelectSemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent= getIntent();
        String message=intent.getStringExtra(BCA.MSG);
        Intent intentSelectSem =new Intent(SelectSem.this,SubjectList.class);
//        filtering the course then after notes or paper by checking the message string
        if(message.contains("bca")){
            if(message.contains("Notes")){
                binding.btnSem1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  data="bcaNotesSem1";
                        CourseDetails.SEM=1;
                        //intentSelectSem.putExtra(MSG,data);
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data="bcaNotesSem2";
                        //intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=2;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data="bcaNotesSem3";
                        //intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=3;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data="bcaNotesSem4";
                        //intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=4;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data="bcaNotesSem5";
                        //intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=5;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //data="bcaNotesSem6";
                        //intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=6;
                        startActivity(intentSelectSem);

                    }
                });

//                Toast.makeText(this, "bca notes", Toast.LENGTH_SHORT).show();
            }
            else if(message.contains("Paper")){

                binding.btnSem1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem1";
                        CourseDetails.SEM=1;
//                        intentSelectSem.putExtra(MSG,data);
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem2";
//                        intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=2;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem3";
//                        intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=3;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem4";
//                        intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=4;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem5";
//                        intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=5;
                        startActivity(intentSelectSem);

                    }
                });
                binding.btnSem6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        data="bcaPaperSem6";
//                        intentSelectSem.putExtra(MSG,data);
                        CourseDetails.SEM=6;
                        startActivity(intentSelectSem);

                    }
                });
            }

        }

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //Reseting the variable to navigate correctly
        CourseDetails.PAPER=0;
        CourseDetails.NOTES=0;
//        Toast.makeText(SelectSem.this, "Back button pressed", Toast.LENGTH_SHORT).show();
    }
}