package com.android.project1.fsth.pythonetutorial;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Refernce extends AppCompatActivity {
    shared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared = new shared(this);

      /*  if(shared.loadnightmode()==true)
        {
            setTheme(R.style.darkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
*/       shared.LoadMode();//Dark Mode

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setTitle("Reference");
        setContentView(R.layout.activity_refernce);
        WebView refer=(WebView)findViewById(R.id.readonly);
        refer.loadUrl("file:///android_asset/refernce.html");
    }

   public void onBackPressed() {
        startActivity(new Intent(this,Main4Activity.class)); finish();

        //super.onBackPressed();
    }
}
