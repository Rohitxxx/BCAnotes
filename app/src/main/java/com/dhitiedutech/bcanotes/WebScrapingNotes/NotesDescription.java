package com.dhitiedutech.bcanotes.WebScrapingNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.databinding.ActivityNotesDescriptionBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NotesDescription extends AppCompatActivity {

    String url;
    ActivityNotesDescriptionBinding binding;
    SweetAlertDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNotesDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        url=getIntentData();
        new Bg().execute(url);
    }
    public String getIntentData(){
        String url=" ";
        try{
            Intent intent=getIntent();
            url=intent.getStringExtra("url");
        }catch (Exception e){
            Toast.makeText(NotesDescription.this, "something went wrong: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            return url;
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void setWebView(){
        binding.descriptionWebView.getSettings().setJavaScriptEnabled(true);
        binding.descriptionWebView.getSettings().setSupportZoom(true);
    }
    class Bg extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(NotesDescription.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading ...");
            pDialog.setCancelable(true);
            pDialog.show();
//            Toast.makeText(NotesDescription.this, url, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                if(url!=null){
                    Document document= Jsoup.connect(strings[0]).get();
                    document.select(".pre-btn").remove();
                    document.select(".nxt-btn").remove();
                    document.select("#google-top-ads").remove();
                    document.select("#google-bottom-ads").remove();
                    document.select("#load").remove();
                    document.select(".center-aligned tutorial-menu").remove();
                    document.select("form").remove();
                    document.select("#books").remove();

                    Elements imgElements = document.select("img"); //adding base url to the image...https://tutorialspoint.com is missing
                    for (Element element : imgElements) {
                        element.attr("src", element.attr("abs:src"));
                    }
//                    return document.getElementById("city").html();
                    return document.getElementsByClass("mui-col-md-6 tutorial-content").html();
                }

            } catch (Exception e) {
//                Toast.makeText(NotesDescription.this, "something went wrong : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            binding.descriptionWebView.getSettings().setJavaScriptEnabled(true);
            binding.descriptionWebView.getSettings().setSupportZoom(true);
            if(s==null){
                s="<h2 style='text-align:center'>404! file not found</h2>"; //if no data found
            }
            s=s.replaceAll("Live Demo","");
            s=s.replaceAll("Tutorial","");
            binding.descriptionWebView.loadDataWithBaseURL(null,s,"text/html", "utf-8", null);
//            binding.textView13.setText(url);
//            binding.textView13.setTextIsSelectable(true);
        }
    }
}