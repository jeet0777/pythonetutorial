package com.android.project1.fsth.pythonetutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity extends Activity {
 private static int SPLASH=2000;
 shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=new shared(this);
        shared.LoadMode();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
       /* WebView wv=(WebView)findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true
        );
        wv.loadUrl("file:///android_asset/maindesg.html");
       */ new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(i);
                finish();
            }
        },SPLASH);
    }
}
