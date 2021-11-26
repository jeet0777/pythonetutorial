package com.android.project1.fsth.pythonetutorial;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class About extends AppCompatActivity {
shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("About App");
        shared =new shared(this);
       /* if(shared.loadnightmode())
            setTheme(R.style.darkTheme);
        else{
            setTheme(R.style.AppTheme);
        }
       */
        shared.LoadMode();//Dark Mode
        setContentView(R.layout.activity_about);


    }

    @Override
     public void onBackPressed() {
        startActivity(new Intent(this,Main4Activity.class)); finish();

        //super.onBackPressed();
    }
}
