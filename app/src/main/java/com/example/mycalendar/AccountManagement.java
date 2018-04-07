package com.example.mycalendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class AccountManagement extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail, editTextPassword, editTextPassword2;
    private FirebaseAuth mAuth;
    private Button createAccount;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_account);

        progressDialog = new ProgressDialog(this);
        createAccount = (Button)findViewById(R.id.createAccount);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPassword2 = findViewById(R.id.passwordVerify);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.createAccount).setOnClickListener(this);
        findViewById(R.id.logIn).setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
    }

    private void registerUser(){
        progressDialog.setMessage("Validating Information");
        progressDialog.show();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();

        /**********
         * Ensures valid data
         */
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password2)) {
            editTextPassword2.setError("Password required");
            editTextPassword2.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length must be 6");
            editTextPassword.requestFocus();
            return;
        }

        // does this work (!=) or the (.equals)?
        if (!password.equals(password2)){
            editTextPassword.setError("Passwords do not match");
            editTextPassword.requestFocus();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountManagement.this, dailyPresenter.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    progressDialog.hide();
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "The account is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error occurred. User not registered", Toast.LENGTH_SHORT).show();
                        Log.i("Test", task.getException().getMessage());
                    }
                }
            }
        });
        progressDialog.hide();
    }

    // Nothing has to necessarily go in here. Might be helpful for testing, however
    // "https://github.com/firebase/quickstart-android/blob/de3ae39c1c2eff3bc66c55b70eef7cbda50fb047/auth/app/src/main/java/com/google/firebase/quickstart/auth/EmailPasswordActivity.java#L71-L77"
    private void updateUI(FirebaseUser user) {
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.logIn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.createAccount:
                registerUser();
                break;
        }
    }
}
