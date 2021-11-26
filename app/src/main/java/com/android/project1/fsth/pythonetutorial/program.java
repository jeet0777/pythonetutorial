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
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.lang.reflect.Method;

public class program extends AppCompatActivity {
 shared shared;
 WebView wvp2;
    font font_prefer;
    public LinearLayout container;
    Button nextbutton,closebutton;
    private EditText findbox;
    private  static  final int SEARCH_ID= Menu.FIRST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        shared = new shared(this);
        /*if(shared.loadnightmode())
        {
            setTheme(R.style.darkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        */
        shared.LoadMode();//Dark Mode
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Programs");
        setContentView(R.layout.activity_program);
         wvp2=(WebView)findViewById(R.id.wvp1);
        if(shared.loadnightmode()==true)
        {   wvp2.setBackgroundColor(Color.BLACK);
            //setTheme(R.style.darkTheme);
        }
        else{
            wvp2.setBackgroundColor(Color.WHITE);
            //setTheme(R.style.AppTheme);
        }

        font_prefer=new font(this);
        int p=font_prefer.loadfont();
         wvp2.getSettings().setDefaultFontSize((int)p);
       // wvp2.setBackgroundColor(Color.GRAY);

        wvp2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wvp2.getSettings().setBuiltInZoomControls(true);
        wvp2.getSettings().setJavaScriptEnabled(true);
        wvp2.getSettings().setDomStorageEnabled(true);
        wvp2.setWebViewClient(new WebViewClient());
        wvp2.loadUrl("http://paythone.000webhostapp.com/prg/prg.html");
        wvp2.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        final ProgressBar pgb2=(ProgressBar)findViewById(R.id.pgb2);

        if(ni!=null && ni.isConnected())
        {
            wvp2.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            wvp2.loadUrl("http://paythone.000webhostapp.com/prg/prg.html");

            wvp2.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pgb2.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    pgb2.setVisibility(View.GONE);
                    super.onPageFinished(view, url);

                    if(shared.loadnightmode())
                    wvp2.evaluateJavascript("document.body.style.color=\"white\"",null);
                    if(shared.loadnightmode() && !wvp2.canGoBack()) {
                 //       wvp2.evaluateJavascript("javascript:document.body.style.backgroundColor=\"black\"", null);
                   wvp2.setBackgroundColor(Color.BLACK);
                        wvp2.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                                "for(var i=0; i<x.length; i++){\n" +
                                "x[i].style.color=\"white\";\n}", null);
                    //    wvp2.evaluateJavascript("javascript: var y= document.getElementsByTagName(\"div\");\n" +
                      //          "for(var j=0; j<y.length; j++){\n" +
                        //        "y[j].style.color=\"white\";\n" +
                          //      "y[j].style.backgroundColor=\"black\";}\n", null);

                    }//nested if
                    //trial
                    if(shared.loadnightmode() && wvp2.canGoBack()){

                            wvp2.evaluateJavascript("javascript: var y= document.getElementsByTagName(\"div\");\n" +
                                  "for(var j=0; j<y.length; j++){\n" +
                                  "y[j].style.backgroundColor=\"black\";}\n", null);
                //        wvp2.setBackgroundColor(getResources().getColor(R.color.webview_bg_for_program_section));

                    }//end trial
                }
            });

        }//closing if*///offline mode
        else{ wvp2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); pgb2.setVisibility(View.GONE);

        wvp2.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if(shared.loadnightmode())
                        wvp2.evaluateJavascript("document.body.style.color=\"white\"",null);
                    if(shared.loadnightmode() && !wvp2.canGoBack()) {
                       // wvp2.evaluateJavascript("javascript:document.body.style.backgroundColor=\"black\"", null);
                        wvp2.setBackgroundColor(Color.BLACK);
                        wvp2.evaluateJavascript("javascript: var x= document.getElementsByTagName(\"a\");\n" +
                                "for(var i=0; i<x.length; i++){\n" +
                                "x[i].style.color=\"white\";\n}", null);
                        //wvp2.evaluateJavascript("javascript: var y= document.getElementsByTagName(\"div\");\n" +
                          //      "for(var j=0; j<y.length; j++){\n" +
                            //    "y[j].style.color=\"white\";\n" +
                              //  "y[j].style.backgroundColor=\"black\";}\n", null);

                    }//nested if
                    if(shared.loadnightmode() && wvp2.canGoBack()){

                        wvp2.evaluateJavascript("javascript: var z= document.getElementsByTagName(\"div\");\n" +
                                "for(var k=0; k<z.length; k++){\n" +
                                "z[k].style.backgroundColor=\"black\";}\n", null);

                    }//end trial
                }
            });
        }


    }
    @Override //option menu
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
        final   ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)wvp2.getLayoutParams();

        p.topMargin=60;
        p.height=10000;
        wvp2.setLayoutParams(p);
        container=(LinearLayout) findViewById(R.id.linearlayoutprg);
        //LinearLayout container=new LinearLayout(tutorial.this);
        //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.)
        nextbutton=new Button(this);
        nextbutton.setText("next");
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wvp2.findNext(true);
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
                    wvp2.findAll(findbox.getText().toString());
                try{

                    Method m=WebView.class.getMethod("setFindisup",Boolean.TYPE);
                    m.invoke(wvp2,true);

                }catch (Exception ignored){}
                return false;
            }
        });
        container.addView(findbox);

        // getActionBar().setCustomView(container);

    }

    @Override
    public void onBackPressed() {
        if(wvp2.canGoBack())
            wvp2.goBack();
        else
        super.onBackPressed();
    }

}
