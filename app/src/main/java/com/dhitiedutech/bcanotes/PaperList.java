package com.dhitiedutech.bcanotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dhitiedutech.bcanotes.Classes.RecyclerItemClickListener;
import com.dhitiedutech.bcanotes.Adapters.PdfListAdapter;
import com.dhitiedutech.bcanotes.Modals.CourseDetails;
import com.dhitiedutech.bcanotes.Modals.PdfListModel;
import com.dhitiedutech.bcanotes.R;
import com.dhitiedutech.bcanotes.databinding.ActivityPaperListBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PaperList extends AppCompatActivity {
    public static final String MSG="com.example.bcanotes.MSG";
    ActivityPaperListBinding binding;
    String[] filterPaperlist=new String[5];
    Integer paperNo=0;
    FirebaseStorage storage;
    StorageReference ref;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPaperListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(PaperList.this);
        progressDialog.setTitle("Loading paper list");
        progressDialog.setMessage("Please be patience, loading paper list...");
        progressDialog.show();
        ArrayList<PdfListModel> pdflist = new ArrayList<>();

//        old code-- fetch data from asserts folder
     /*   final AssetManager assetManager = getAssets();
        try {
            // for assets folder add empty string
            filelist = assetManager.list("");
            // for assets/subFolderInAssets add only subfolder name
             filelistInSubfolder = assetManager.list("paper");
            if (filelist == null) {
                // dir does not exist or is not a directory
            } else {
                filterPaperlist=new String[5];
                for (int i=0; i<filelistInSubfolder.length; i++) {
                    // Get filename of file or directory
//                    String filename = filelist[i];
                    if(filelistInSubfolder[i].contains("bcaSem"+ CourseDetails.SEM+"Sub"+CourseDetails.SUBJECT)){

                        filterPaperlist[paperNo]=filelistInSubfolder[i];
                        pdflist.add(new PdfListModel(paperNo+1,R.drawable.paper,filterPaperlist[paperNo]));
                        paperNo++;
                    }

                }
            }

            // if(filelistInSubfolder == null) ............

        } catch (IOException e) {
            e.printStackTrace();
        }  */

//        new code-- fetch data from server
        storage=FirebaseStorage.getInstance();
        ref= storage.getReference()
                .child("bca")
                .child("paper")
                .child("sem"+CourseDetails.SEM);
        ref.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {

                    @Override
                    public void onSuccess(ListResult listResult) {
                        progressDialog.dismiss();
                        for(StorageReference item : listResult.getItems()){
//                            Log.d("items",item.getName());
                            // filtering the required paper list
                            if(item.getName().contains("bcaSem"+ CourseDetails.SEM+"Sub"+CourseDetails.SUBJECT)){
                                filterPaperlist[paperNo]=item.getName();

                                paperNo++;
                            }
                        }
                        for (Integer i=0;i<paperNo;i++){
                            pdflist.add(new PdfListModel(i+1, R.drawable.paper,filterPaperlist[i]));
                        }
                        Log.d("paperNo",paperNo.toString());
                        PdfListAdapter adapter= new PdfListAdapter(pdflist,PaperList.this);
                        binding.recyclerView.setAdapter(adapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PaperList.this);
                        binding.recyclerView.setLayoutManager(linearLayoutManager);
                        binding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                                PaperList.this, binding.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                Toast.makeText(PaperList.this, Integer.valueOf(position).toString(), Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(PaperList.this,DocumentViewer.class);
                                intent.putExtra("MSG",filterPaperlist[position]);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        }
                        ));
//                        Log.d("paperlist",filterPaperlist[paperNo]);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Log.d("error",e.getMessage());
                    }
                });
    }
}