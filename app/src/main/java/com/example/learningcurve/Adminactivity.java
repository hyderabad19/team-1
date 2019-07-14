package com.example.learningcurve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminactivity extends AppCompatActivity {
    private Button srchbtn;
    private Button logoutbtn;

    private Button apprvbtn;
    private Button uploadctnt;
    private Button delctnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);

        srchbtn=findViewById(R.id.admin_srchbtn);
        apprvbtn=findViewById(R.id.admin_aprbtn);
        logoutbtn=findViewById(R.id.admin_logout);
        uploadctnt=findViewById(R.id.admin_upbtn);
        delctnt=findViewById(R.id.admin_del);


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logi=new Intent(Adminactivity.this,LoginAc.class);
                startActivity(logi);
                finish();
            }
        });

        uploadctnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upl=new Intent(Adminactivity.this,UploadActivity.class);
                startActivity(upl);
            }
        });
    }
}
