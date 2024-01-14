package com.dhitiedutech.bcanotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Adapters.CategoryAdapter;
import com.dhitiedutech.bcanotes.Adapters.CategoryAdapter3;
import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Modals.Category;
import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.WebScrapingNotes.NotesByWebScraping;
import com.dhitiedutech.bcanotes.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    varialble for exit functionality
    private long backPressedTime;
    AdView mAdView;
    ActivityMainBinding binding;
    private Toast backToast;
    // code for double backpress button for exiting the app
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();

            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    ImageView ivBca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        SharedPreferences sharedPreferences = getSharedPreferences("name",MODE_PRIVATE);
        String uname= sharedPreferences.getString("name","Student");
        binding.tvName.setText(uname);
        setMainRecyclerView();
        setMiscRecyclerView();
//        ivBca=(ImageView) findViewById(R.id.imgViewBcaCourse);
//        ivBca.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(MainActivity.this, "Bca course is clicked"+ivBca, Toast.LENGTH_SHORT).show();
//
//                Log.d("mes","intent object created");
//                Intent intentBca = new Intent(MainActivity.this,BCA.class);
//                CourseDetails.COURSE=1;
//                startActivity(intentBca);
//
//            }
//        });
//        binding.allUniversityImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Notes.class));
//            }
//        });
//        binding.imgViewNews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    String url= "https://start.mgkvp.ac.in/Notifications/NoticeBoard";
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                }catch (Exception e){
//                    Log.d("intent",e.getMessage());
//                }
//            }
//        });
//        binding.imgResults.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    String url="https://erp.mgkvp.online/Student/StudentLogin.aspx";
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                }catch (Exception e){
//                    Log.d("intent",e.getMessage());
//                }
//            }
//        });
//        binding.imgShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String str = "https://play.google.com/store/apps/details?id=com.dhitiedutech.bcanotes";
//                                Intent sendIntent = new Intent();
//                                sendIntent.setAction(Intent.ACTION_SEND);
//                                sendIntent.putExtra(Intent.EXTRA_TEXT,str);
//                                sendIntent.setType("text/plain");
//                                Intent shareIntent = Intent.createChooser(sendIntent, "Share this app...");
//                                startActivity(shareIntent);
//            }
//        });
        binding.txtMinor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DocumentViewer.class);
                intent.putExtra("detail","minor.pdf");
                startActivity(intent);
            }
        });
        binding.txtMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DocumentViewer.class);
                intent.putExtra("detail","major.pdf");
                startActivity(intent);
            }
        });
        binding.txtSummer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DocumentViewer.class);
                intent.putExtra("detail","summer.pdf");
                startActivity(intent);
            }
        });

        // ads sdk initializing
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
//                Toast.makeText(MainActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
//                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setting) {
            // do something here
//            Toast.makeText(this, "Setting is clicked", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(MainActivity.this,Setting.class);
            startActivity(intent);
        }
        if (id == R.id.bell) {
            // do something here
            Toast.makeText(this, "No new notification", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void setMainRecyclerView(){
//        Toast.makeText(MainActivity.this, "recyclerView", Toast.LENGTH_SHORT).show();
        ArrayList<Category> list=new ArrayList<>();
        list.add(new Category(R.drawable.bca,"BCA"));
        list.add(new Category(R.drawable.notes,"All University Notes"));
        CategoryAdapter adapter=new CategoryAdapter(this,list);
        binding.mainRecyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        binding.mainRecyclerView.setLayoutManager(gridLayoutManager);
        binding.mainRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, binding.mainRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0){
                    Log.d("mes","intent object created");
                    Intent intentBca = new Intent(MainActivity.this,BCA.class);
                    CourseDetails.COURSE=1;
                    startActivity(intentBca);
                }else if(position==1){
                    startActivity(new Intent(MainActivity.this, Notes.class));
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
    public void setMiscRecyclerView(){
//        Toast.makeText(MainActivity.this, "recyclerView", Toast.LENGTH_SHORT).show();
        ArrayList<Category> list=new ArrayList<>();
        list.add(new Category(R.drawable.paper_,"News"));
        list.add(new Category(R.drawable.paper,"Results"));
        list.add(new Category(R.drawable.network,"Share"));
        CategoryAdapter adapter=new CategoryAdapter(this,list);
        binding.miscRecyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        binding.miscRecyclerView.setLayoutManager(gridLayoutManager);
        binding.miscRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, binding.miscRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0){
                    try {
                        String url= "https://start.mgkvp.ac.in/Notifications/NoticeBoard";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }catch (Exception e){
                        Log.d("intent",e.getMessage());
                    }
                }else if(position==1){
                    try {
                        String url="https://erp.mgkvp.online/Student/StudentLogin.aspx";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }catch (Exception e){
                        Log.d("intent",e.getMessage());
                    }
                }else if(position==2){
                    String str = "https://play.google.com/store/apps/details?id=com.dhitiedutech.bcanotes";
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,str);
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, "Share this app...");
                    startActivity(shareIntent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }



}