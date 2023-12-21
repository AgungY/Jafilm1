package com.example.jafilm1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.Email);
        editPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                loginUser(email, password);
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Semua Data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginUser(String email, String password) {
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(true);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    reload();
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal mendapatkan informasi pengguna", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish(); // Tambahan untuk menutup activity login setelah berhasil login
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }
}
