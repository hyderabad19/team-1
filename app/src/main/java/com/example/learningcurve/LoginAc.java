package com.example.learningcurve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAc extends AppCompatActivity {





    private EditText loginemail;
    private EditText loginpassword;
    private Button loginbtn;
    private Button loginregbtn;
    private FirebaseAuth mauth;

    private ProgressBar loginprogbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemail=(EditText)findViewById(R.id.reg_email);
        loginpassword=(EditText)findViewById(R.id.reg_pass);
        loginbtn=(Button)findViewById(R.id.reg_btn);
        loginregbtn=(Button)findViewById(R.id.reg_login_btn);
        mauth= FirebaseAuth.getInstance();
        loginprogbar=(ProgressBar)findViewById(R.id.reg_prog_bar);
        loginprogbar.setVisibility(View.INVISIBLE);


        loginregbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent regi=new Intent(LoginAc.this,RegisterAc.class);
           startActivity(regi);

            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final String loginemailtext=loginemail.getText().toString();
              final  String loginpasstext=loginpassword.getText().toString();

                if(loginemailtext.equals("Admin@gmail.com") && loginpasstext.equals("Admin@123"))
                {
                                      Intent admi=new Intent(LoginAc.this,Adminactivity.class);
                                      startActivity(admi);
                                      finish();
                }
                else if(!TextUtils.isEmpty(loginemailtext) && !TextUtils.isEmpty(loginpasstext)) {

                    loginprogbar.setVisibility(View.VISIBLE);
                    mauth.signInWithEmailAndPassword(loginemailtext,loginpasstext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {

        Intent regi=new Intent(LoginAc.this,MainActivity.class);
        startActivity(regi);
        finish();

                       }
                       else
                       {
                           String errormsg=task.getException().getMessage();
                           Toast.makeText(LoginAc.this, "Error: "+errormsg, Toast.LENGTH_SHORT).show();


                       }
                       loginprogbar.setVisibility(View.INVISIBLE);
                        }

                    });
                }

                }
        });



    }
}
