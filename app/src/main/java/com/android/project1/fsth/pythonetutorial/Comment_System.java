package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Comment_System extends AppCompatActivity {
 shared shared;
    //TextView tv=(TextView)findViewById(R.id.hiddentv);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("comments");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        shared=new shared(this);
        if(shared.loadnightmode()==true)
        {
            setTheme(R.style.darkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_comment__system);
        WebView cns=(WebView)findViewById(R.id.cmns);
        cns.getSettings().setJavaScriptEnabled(true);
        cns.getSettings().setBuiltInZoomControls(true);
       // cns.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        cns.getSettings().setSupportZoom(true);
        cns.setWebViewClient(new WebViewClient());
        cns.loadUrl("http://paythone.000webhostapp.com/coment/");
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        final ProgressBar pgb7=(ProgressBar)findViewById(R.id.pgb7);
        ImageView img1=(ImageView)findViewById(R.id.hidden);
        TextView tv=(TextView)findViewById(R.id.hiddentv);

        if(ni!=null && ni.isConnected())
        {
            cns.loadUrl("http://paythone.000webhostapp.com/coment/");
                if(img1.getVisibility()==View.VISIBLE){
                    img1.setVisibility(View.GONE);
                }
                if(tv.getVisibility()==View.VISIBLE){
                    tv.setVisibility(View.GONE);
                }
            cns.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pgb7.setVisibility(View.VISIBLE);
                }



                @Override
                public void onPageFinished(WebView view, String url) {
                    pgb7.setVisibility(View.GONE);
                    super.onPageFinished(view, url);
                }
            });

        }//closing if*/
        else{  pgb7.setVisibility(View.GONE);
            cns.loadUrl("file:///android_asset/white_page.html");
            cns.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            img1.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
//            img1.setVisibility(View.VISIBLE);}


    }

    }}

