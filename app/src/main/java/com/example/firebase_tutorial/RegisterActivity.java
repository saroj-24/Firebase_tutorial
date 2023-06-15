package com.example.firebase_tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText email,pw;
    Button register;

    private FirebaseAuth auth; //dependency of firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        pw = findViewById(R.id.pw);
        register = findViewById(R.id.reg_btn);

        auth = FirebaseAuth.getInstance();// initialize the auth variable

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = email.getText().toString();
                String password  = pw.getText().toString();
                if(email_text.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"please enter email",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"please enter password",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this," password is too short",Toast.LENGTH_SHORT).show();
                }
                else {
                    registerFun(email_text,password);
                }

            }
        });
    }
    private void registerFun(String email,String pw)
    {
        //This function will create new user in firebase
           auth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                       startActivity( new Intent(RegisterActivity.this,MainActivity.class));
                       finish();
                   }
                   else {
                       Toast.makeText(RegisterActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                   }
               }
           });
    }
}