package com.dhitiedutech.bcanotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dhitiedutech.bcanotes.Modals.Users;
import com.dhitiedutech.bcanotes.databinding.ActivitySigninBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    private FirebaseAuth auth;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    Users user;
    ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Login");

        binding.tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this,Login_signup.class);
                startActivity(intent);
            }
        });
        auth =FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        ref= database.getReference();

        progressDialog = new ProgressDialog(Signin.this);
        progressDialog.setTitle("Logging in");
        progressDialog.setMessage("We are logging you in");

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etEmail.getText().toString().equals("") || binding.etPassword.getText().toString().equals("")) {
                    Toast.makeText(Signin.this, "Please enter your details", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    Log.d("inside click","inside click");
                    auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(), binding.etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("com","complete");
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        Log.d("task","success");
                                        Toast.makeText(Signin.this, "Success", Toast.LENGTH_SHORT).show();
                                        Log.d("TAG", "signInWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                        ref.child(user.getUid()).child("userName").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Log.d("name","name");
                                                Intent intent = new Intent(Signin.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
//                                                Users users = new Users(binding.etEmail.getText().toString(),binding.etPassword.getText().toString(),snapshot.getValue(String.class));
//                                                saveNameToLocal(snapshot.getValue(String.class));
//                                                Log.d("user",snapshot.getValue(String.class));
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Log.d("nameN","name not error");
                                                Toast.makeText(Signin.this, "Failed", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                    else{
                                        Log.d("not","something went wrong");
                                        Toast.makeText(Signin.this, "Wrong Id or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        // creating account with google
        gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }
    int RC_SIGN_IN=65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    String TAG="TAG";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();

                            saveNameToLocal(user.getDisplayName());

                            Intent intent = new Intent(Signin.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(Signin.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }
                    }
                });
    }
    public void saveNameToLocal(String uName){
        SharedPreferences sharedPreferences =getSharedPreferences("name",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(uName!=null) {
            editor.putString("name", uName);
        }
        else
            editor.putString("name","Student");
        editor.apply();
    }



}