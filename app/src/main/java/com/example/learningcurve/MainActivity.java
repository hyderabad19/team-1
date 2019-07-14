package com.example.learningcurve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mauth;
    private Toolbar maintool_bar;
    private Button btnid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();
        maintool_bar=findViewById(R.id.tool_bar_main);
        setSupportActionBar(maintool_bar);
        getSupportActionBar().setTitle("Learning Curve");
        btnid=findViewById(R.id.button_viewcnt);
        btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(MainActivity.this,retreivedata.class);
                startActivity(i2);

            }
        });
    }


    protected void onStart() {
        super.onStart();
        FirebaseUser current= FirebaseAuth.getInstance().getCurrentUser();
        if(current== null)
        {
            Intent loginint=new Intent(MainActivity.this,LoginAc.class);
            startActivity(loginint);
            finish();
            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.menu_logout:
                mauth.signOut();
                Intent loginintent =new Intent(MainActivity.this,LoginAc.class);
                startActivity(loginintent);
                finish();
                return true;
            case R.id.menu_settings:
                Intent dumin=new Intent(MainActivity.this,SetupAcc.class);
                startActivity(dumin);
                return true;




            default:
                return false;


        }



    }


}
