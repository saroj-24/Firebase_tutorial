package com.example.firebase_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

     EditText email,pw;
    Button loginbtn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = this.<EditText> findViewById(R.id.login_email);
        pw= this.<EditText>findViewById(R.id.login_pw);
        loginbtn= this.<Button>findViewById(R.id.rlogin_btn);

        auth = FirebaseAuth.getInstance();//Returns a user identifier as specified by the authentication provider.

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_text = email.getText().toString();
                String pw_text = pw.getText().toString();
                
                loginFun(email_text,pw_text);

            }
        });
    }

    private void loginFun(String email,String pw) {

        //sigwithemailandpassword takes two parameter email and password and return success as email and password matched.
         auth.signInWithEmailAndPassword(email,pw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
             @Override
             public void onSuccess(AuthResult authResult) {
                 Toast.makeText(LoginActivity.this, "login successfully ", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(LoginActivity.this,MainActivity.class));
                 finish();
             }
         });

    }
}