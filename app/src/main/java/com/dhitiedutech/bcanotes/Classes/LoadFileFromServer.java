package com.dhitiedutech.bcanotes.Classes;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LoadFileFromServer {
    FirebaseStorage storage;
    StorageReference ref;
    public LoadFileFromServer(){
        storage=FirebaseStorage.getInstance();
    }
    public String LoadFileList(String path){
       // ref=storage.getReference().child(path)
         //       .listAll()
        return "";
    }

}
