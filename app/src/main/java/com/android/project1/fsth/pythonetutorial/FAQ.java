package com.android.project1.fsth.pythonetutorial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.lang.reflect.Method;

public class FAQ extends AppCompatActivity {
    shared shared; WebView wvf;
    font font_prefer;
    public LinearLayout container;
    Button nextbutton,closebutton;
    private EditText findbox;
    private  static  final int SEARCH_ID= Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=new shared(this);
        /*if(shared.loadnightmode()==true)
        {
            setTheme(R.style.darkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }*/   shared.LoadMode();//Dark Mode

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("FAQ");
        setContentView(R.layout.activity_faq);

        font_prefer=new font(this);

        int f=font_prefer.loadfont();
        wvf=(WebView)findViewById(R.id.wv1f);
        if(shared.loadnightmode()==true)
        {   wvf.setBackgroundColor(Color.BLACK);
            //setTheme(R.style.darkTheme);
        }
        else{
            wvf.setBackgroundColor(Color.WHITE);
            setTheme(R.style.AppTheme);
        }
        wvf.getSettings().setDefaultFontSize((int)f);
      wvf.getSettings().setJavaScriptEnabled(true);
      wvf.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
      wvf.getSettings().setSupportZoom(true);
      wvf.getSettings().setBuiltInZoomControls(true);
      wvf.setWebViewClient(new WebViewClient());
      wvf.canGoBackOrForward(3);
      wvf.getVisibility();
          wvf.loadUrl("https://paythone.000webhostapp.com/faq/que.html");
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        final ProgressBar pgb3=(ProgressBar)findViewById(R.id.pgb3);

        if(ni!=null && ni.isConnected())
        {
            wvf.loadUrl("https://paythone.000webhostapp.com/faq/que.html");

            wvf.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pgb3.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    pgb3.setVisibility(View.GONE);
                    super.onPageFinished(view, url);
                    if(shared.loadnightmode()) {
                        wvf.evaluateJavascript("javascript:document.body.style.color=\"white\"", null);
                        wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"p\");\n" +
                                "for(var i=0; i<x.length;i++){\n" +
                                "x[i].style.color=\"white\";\n" +
                                "x[i].style.backgroundColor=\"black\";}\n", null);

                    }if(shared.loadnightmode() && !wvf.canGoBack()) {
                        //wvf.evaluateJavascript("javascript:document.body.style.backgroundColor=\"gray\"", null);
                        wvf.setBackgroundColor(Color.BLACK);
                        wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                                "for(var i=0; i<x.length; i++){\n" +
                                "x[i].style.color=\"white\";\n}", null);

                       // wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"div\");\n" +
                         //       "for(var i=0; i<x.length;i++){\n" +
                           //     "x[i].style.backgroundColor=\"white\";\n" +
                             //   "x[i].style.backgroundColor=\"black\";}\n", null);
                    }//nested if
                }
            });

        }//closing if*/
        else{ wvf.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); pgb3.setVisibility(View.GONE);
         wvf.setWebViewClient(new WebViewClient(){
             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                 if(shared.loadnightmode()) {
                     wvf.evaluateJavascript("javascript:document.body.style.color=\"white\"", null);
                     wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"p\");\n" +
                             "for(var i=0; i<x.length;i++){\n" +
                             "x[i].style.color=\"white\";\n" +
                             "x[i].style.backgroundColor=\"black\";}\n", null);

                 }if(shared.loadnightmode() && !wvf.canGoBack()) {
                     //wvf.evaluateJavascript("javascript:document.body.style.backgroundColor=\"gray\"", null);
                     wvf.setBackgroundColor(Color.BLACK);
                     wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                             "for(var i=0; i<x.length; i++){\n" +
                             "x[i].style.color=\"white\";\n}", null);

                   //  wvf.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"div\");\n" +
                     //        "for(var i=0; i<x.length;i++){\n" +
                       //      "x[i].style.backgroundColor=\"white\";\n" +
                         //    "x[i].style.backgroundColor=\"black\";}\n", null);
                 }//nested if

             }
         });

        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflates=getMenuInflater();
        inflates.inflate(R.menu.other,menu);
        menu.add(0,SEARCH_ID,0,"search");
        //hide bookmark icon
        MenuItem please = menu.findItem(R.id.please);
        please.setVisible(false);
        //hide last bookmark activity option
        MenuItem keyword=menu.findItem(R.id.youshouldwork);
        keyword.setVisible(false);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    //selected items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.rm:
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().hide();
                return true;
            case R.id.shre:
                Intent sharebtn=new Intent(Intent.ACTION_SEND);
                sharebtn.setType("text/plain");
                String url="App is in devlopment";
                sharebtn.putExtra(Intent.EXTRA_SUBJECT,"Subject/Title");
                sharebtn.putExtra(Intent.EXTRA_TEXT,"hellow");
                startActivity(Intent.createChooser(sharebtn,"Choose sharing method"));
                return  true;
            case SEARCH_ID:
                search(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onBackPressed() {
        getSupportActionBar().show();
        super.onBackPressed();
    }*/

    public void search(){
        // EditText editTex;
        //editTex=new EditText(this);
        //getActionBar().setCustomView(editTex);
        //getActionBar().setDisplayShowTitleEnabled(false);
//if(shared.loadnightmode()==true){ findbox.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);}else{}
        final   ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)wvf.getLayoutParams();

        p.topMargin=60;
        p.height=10000;
        wvf.setLayoutParams(p);
        container=(LinearLayout) findViewById(R.id.linearlayoutfaq);
        //LinearLayout container=new LinearLayout(tutorial.this);
        //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.)
        nextbutton=new Button(this);
        nextbutton.setText("next");
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wvf.findNext(true);
            }
        });
        container.addView(nextbutton);

        closebutton =new Button(this);
        closebutton.setText("close");
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                p.topMargin=0;
                p.height=10000;
                // tut.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        });


        container.addView(closebutton);

        //  ColorStateList colorStateList=ColorStateList.valueOf(245);

        findbox=new EditText(this);
        findbox.setMinEms(30);
        findbox.setSingleLine(true);
        findbox.setHint("Search");
        findbox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&((keyCode==KeyEvent.KEYCODE_ENTER)))
                    wvf.findAll(findbox.getText().toString());
                try{

                    Method m=WebView.class.getMethod("setFindisup",Boolean.TYPE);
                    m.invoke(wvf,true);

                }catch (Exception ignored){}
                return false;
            }
        });
        container.addView(findbox);

        // getActionBar().setCustomView(container);

    }


    @Override
    public void onBackPressed() {
        if(wvf.canGoBack())
        wvf.goBack();
        else
        super.onBackPressed();
    }
}
