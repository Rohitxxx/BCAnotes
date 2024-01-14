package com.dhitiedutech.bcanotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DocumentViewer extends AppCompatActivity {

    AdView mAdView;
    FirebaseStorage storage;
    StorageReference ref;
    ProgressDialog progressDialog;

    String fileName="";
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_viewer);

        progressDialog =new ProgressDialog(DocumentViewer.this);
        progressDialog.setTitle("Loading file");
        progressDialog.setMessage("Be patience. it may take time to load the file... ");
        progressDialog.show();
        storage = FirebaseStorage.getInstance();
        if(CourseDetails.NOTES==1){
            this.setTitle("BCA Notes");
            fileName="bcaSem"+CourseDetails.SEM+"Sub"+CourseDetails.SUBJECT+"Ch"+CourseDetails.UNIT+".pdf";
            ref= storage.getReference()
                    .child("bca")
                    .child("notes")
                    .child("sem"+CourseDetails.SEM)
                    .child(fileName);

            Log.d("message",fileName);
        }
        else if(CourseDetails.PAPER==1){
//            old code
           /* fileName="paper/"; */
            this.setTitle("BCA Paper");
            Intent intent = getIntent();
            fileName=fileName+intent.getStringExtra("MSG");
            ref= storage.getReference()
                    .child("bca")
                    .child("paper")
                    .child("sem"+CourseDetails.SEM)
                    .child(fileName);

        }
        else if(CourseDetails.COURSE==1){
            this.setTitle("BCA Syllabus");
            fileName="bca.pdf";
            ref = storage.getReference()
                    .child("bca")
                    .child(fileName);
        }
        else if(CourseDetails.COURSE==0){
            ref = storage.getReference().child("bca");
            Intent intent = getIntent();
            String detail=intent.getStringExtra("detail");
            if(detail.contains("minor")){
                this.setTitle("Minor project");
                ref = storage.getReference()
                        .child("bca")
                        .child(detail);

            }
            if(detail.contains("major")){
                this.setTitle("Major project");
                ref = storage.getReference()
                        .child("bca")
                        .child(detail);
            }
            if(detail.contains("summer")){
                this.setTitle("Summer Training");
                ref = storage.getReference()
                        .child("bca")
                        .child(detail);
            }

        }
        else{

            ref = storage.getReference()
                    .child("bca")
                    .child("not found");
            fileName="";
            Toast.makeText(DocumentViewer.this, "Oops! Something went wrong from our side.Try again", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(DocumentViewer.this, "filename is "+fileName, Toast.LENGTH_SHORT).show();
//        Log.d("message",fileName);
        ref.getBytes(10*1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                try {


                    progressDialog.dismiss();
                    PDFView pdf = findViewById(R.id.pdfView);
                    pdf.fromBytes(bytes).enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .defaultPage(0)
                            // allows to draw something on the current page, usually visible in the middle of the screen

                            .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                            .password(null)
                            .scrollHandle(null)
                            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                            // spacing between pages in dp. To define spacing color, set view background
                            .spacing(0)
                            .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                            .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                            .pageSnap(false) // snap pages to screen boundaries
                            .pageFling(false) // make a fling change only a single page like ViewPager
                            .nightMode(false) // toggle night mode
                            .load();
                }catch (Exception e){
                    Log.d("pdf",e.getMessage());
                    Toast.makeText(DocumentViewer.this, "Cannot Load pdf...Please restart application", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Log.d("error",e.getMessage());
                Toast.makeText(DocumentViewer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                TextView textView=findViewById(R.id.txtNotFound);
                ImageView imageView=findViewById(R.id.imgNotFound);
                textView.setText("File not found...\n File will be available soon");
                imageView.setImageResource(R.drawable.cry_m2);
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
//                Toast.makeText(DocumentViewer.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                Log.d("add",adError.toString());
//                Toast.makeText(DocumentViewer.this, "not loaded", Toast.LENGTH_SHORT).show();
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
}