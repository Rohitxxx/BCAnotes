package com.dhitiedutech.bcanotes.WebScrapingNotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Adapters.BarAdapter2;
import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.WebData;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.databinding.ActivityNotesByWebScrapingBinding;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NotesByWebScraping extends AppCompatActivity {

    public WebData webData[];
    ArrayList<WebData> webDataList=new ArrayList<>();
    Integer section=0;
    SweetAlertDialog pDialog;
    ActivityNotesByWebScrapingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNotesByWebScrapingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try{
            Intent intent=getIntent();
            String url=intent.getStringExtra("url");
            new Bg().execute(url);
        }catch (Exception e){
            Toast.makeText(NotesByWebScraping.this, "Something went wrong:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    class Bg extends AsyncTask<String ,String , ArrayList<WebData>> {
        String result="";
        @Override
        protected ArrayList<WebData> doInBackground(String... url) {
            ArrayList<WebData> list=new ArrayList<>();
            String baseUrl="";
            int i=0;
            if(url[0].contains("javatpoint"))
                baseUrl=baseUrl+"https://javatpoint.com/";
            else if(url[0].contains("tutorialspoint"))
                baseUrl=baseUrl+"https://www.tutorialspoint.com";
            try{
                Document document=Jsoup.connect(url[0]).get();
                Elements elements=document.getElementsByAttributeValue("class","toc chapters");
                result=document.getElementsByClass("toc chapters").toString();
//                for(Element e: elements){
//                    Elements ul=elements.get(1).getElementsByTag("li");
//                    for(Element el:ul){
//                        webDataList.add(new WebData(baseUrl+el.getElementsByTag("a").attr("href"),el.getElementsByTag("li").text(),"",R.drawable.notes__,i));
//
//                    }
//                }
                //getting all the class- toc chapters - li (some courses have 2 or 3)
                for(int i1=0;i1<elements.size();i1++){
                    Elements ul=elements.get(i1).getElementsByTag("li");
                    for(int i2=0;i2<ul.size();i2++){
                        webDataList.add(new WebData(baseUrl+ul.get(i2).getElementsByTag("a").attr("href"),ul.get(i2).getElementsByTag("li").text(),"",R.drawable.notes__,i));
                        i++;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
//            try {
//                Document doc= Jsoup.connect(url[0]).get();
//                Elements elementsId=doc.getElementsByAttributeValue("class","leftmenu");
//                Integer j=1;
//                for(Element element: elementsId) {
//                    Elements e = element.getElementsByTag("a");
//                    for (Element el : e) {
//                        webDataList.add(new WebData(baseUrl+el.attr("href"),el.text(),"",R.drawable.notes__,j));
//
//                        j++;
//                    }
//                }
//                return list;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(NotesByWebScraping.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading ...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<WebData> webData) {
            super.onPostExecute(webData);
            try{
                pDialog.dismiss();
//                Toast.makeText(getContext(), webDataList.get(0).getHtmlContent(), Toast.LENGTH_SHORT).show();
                if(webDataList.size()>0){ //checking size because it may not contain values...
                    webDataList.remove(0); //removing the first element because it contains nothing...
                }

                BarAdapter2 adapter=new BarAdapter2(NotesByWebScraping.this,webDataList);
                binding.scrapingRecyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(NotesByWebScraping.this);
                binding.scrapingRecyclerView.setLayoutManager(linearLayoutManager);
                binding.scrapingRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                        NotesByWebScraping.this, binding.scrapingRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=new Intent(NotesByWebScraping.this,NotesDescription.class);
                        intent.putExtra("url",webDataList.get(position).getFileUrl());
                        startActivity(intent);
//                        Toast.makeText(NotesByWebScraping.this, webDataList.get(position).getFileUrl(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }
                ));
            }catch (Exception e){
                Toast.makeText(NotesByWebScraping.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(NotesByWebScraping.this, webDataList.get(0).getFileUrl(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//            binding.tvScrap.setText(webData.get(0).getHtmlContent());
//            Toast.makeText(NotesByWebScraping.this, result, Toast.LENGTH_SHORT).show();
//            binding.textView8.setText(result);
        }
    }

}