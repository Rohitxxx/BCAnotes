package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dhitiedutech.bcanotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class introscreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introscreen);

        getSupportActionBar().hide();
        Thread thread=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        Intent intent = new Intent(introscreen.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(introscreen.this,Login_signup.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };thread.start();
    }
    @Override
    public void onPause() {

        super.onPause();
        finish();
    }
}