package com.example.jafilm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword, editPasswordConf;
    private Button btnRegister, btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.Name);
        editEmail = findViewById(R.id.Email);
        editPassword = findViewById(R.id.Password);
        editPasswordConf = findViewById(R.id.PasswordConf);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);


        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(true);

        mAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(v->{
            finish();
        });
        btnRegister.setOnClickListener(v->{
            if (editName.getText().length()>0 && editEmail.getText().length()>0 && editPassword.getText().length()>0 && editPasswordConf.getText().length()>0){
                if (editPassword.getText().toString().equals(editPasswordConf.getText().toString())){
                    Register(editName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Masukan Password Yang Sama", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getApplicationContext(),"Silahkan Isi Semua Data", Toast.LENGTH_LONG).show();
            }

        });
    }
    private void Register(String Name, String Email, String Password){
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful() && task.getResult()!=null) {
                  FirebaseUser firebaseUser = task.getResult().getUser();
                  if (firebaseUser != null) {
                  UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                          .setDisplayName(Name)
                          .build();
                  firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {

                      }
                  });
              }else{
                  Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_LONG).show();
              }

              }else{
                  Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
              }
            }
        });

    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}