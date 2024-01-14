package com.dhitiedutech.bcanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {

    ActivitySettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("name",MODE_PRIVATE);
        String uname= sharedPreferences.getString("name","Student");
        binding.txtName.setText(uname);

        binding.txtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String url= "https://pages.flycricket.io/bca-notes-0/privacy.html";
                    Intent intent =new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("intent",e.getMessage());
                }
            }
        });
        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences1= getSharedPreferences("name",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
//                    String newName= ;
                    editor.putString("name",binding.editTextName.getText().toString());
                    editor.apply();
                    Toast.makeText(Setting.this, "User Name Updated...Please restart the application.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Setting.this,MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("SharedPreferences",e.getMessage());
                }
            }
        });
    }
}