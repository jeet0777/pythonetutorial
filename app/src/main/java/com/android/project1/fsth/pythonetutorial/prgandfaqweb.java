package com.android.project1.fsth.pythonetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class prgandfaqweb extends AppCompatActivity {
WebView prgandfaqviewweb;
shared shared;
    font font_prefer;
    FloatingActionButton ReaderMode,fab2;
    public LinearLayout container;
    Button nextbutton,closebutton;
    private EditText findbox;
    private  static  final int SEARCH_ID=Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared=new shared(this);
        shared.LoadMode();
        super.onCreate(savedInstanceState);
        font_prefer = new font(this);

        setContentView(R.layout.activity_prgandfaqweb);

    prgandfaqviewweb=findViewById(R.id.prgandfaqwebv);
    prgandfaqviewweb.getSettings().setJavaScriptEnabled(true);
    String    current_page_url="file:///android_asset/";
        StringBuilder stringBuilder = new StringBuilder();

        if(getIntent().getStringExtra("FAQv")!=null) {
            stringBuilder.append(current_page_url +"FAQ/"+ getIntent().getStringExtra("FAQv")
                    + ".htm");

            prgandfaqviewweb.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    if(shared.loadnightmode()) {
                        prgandfaqviewweb.evaluateJavascript("javascript:document.body.style.color=\"white\"", null);
                        prgandfaqviewweb.setBackgroundColor(Color.BLACK);
                    }



                    super.onPageFinished(view, url);
                }
            });
//this is FAQ
        }
        if(getIntent().getStringExtra("PRG")!=null){
            stringBuilder.append(current_page_url +"PRG/"+ getIntent().getStringExtra("PRG")
                    + ".html");

        prgandfaqviewweb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(shared.loadnightmode()) {
                    prgandfaqviewweb.setBackgroundColor(Color.BLACK);
                    prgandfaqviewweb.evaluateJavascript("document.body.style.color=\"white\"", null);

                    prgandfaqviewweb.evaluateJavascript("javascript: var y= document.getElementsByTagName(\"div\");\n" +
                            "for(var j=0; j<y.length; j++){\n" +
                            "y[j].style.backgroundColor=\"black\";}\n", null);

                //for entire Dark Mode no restriction for home or backpage
                }
                //end trial

                super.onPageFinished(view, url);
            }
        });

        }

        int a = font_prefer.loadfont();

        prgandfaqviewweb.getSettings().setDefaultFontSize((int) a);


        prgandfaqviewweb.loadUrl(stringBuilder.toString());



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflates=getMenuInflater();
        inflates.inflate(R.menu.other,menu);
            MenuItem please = menu.findItem(R.id.please);
            please.setVisible(false);//this will hide icon
            //partial way for bookmark hidding dated 20-sept-2020
            //tracking the Code cant means home idc
            MenuItem hideact = menu.findItem(R.id.youshouldwork);
            hideact.setVisible(false);//this will hide Activity



        menu.add(0,SEARCH_ID,0,"search");
        //new Data
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
                ReaderMode=findViewById(R.id.hiddenmode);
                fab2 = findViewById(R.id.fab2);
                ReaderMode.show();
                ReaderMode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fab2.show();//show fab2
                        ReaderMode.hide();//hide this to show new one

                    }
                });

                fab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReaderMode.hide();//hide fab1 to display fab2

                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        getSupportActionBar().show();

                        fab2.hide();//here clicked so the readermode gets off
                        // and this button hides

                    }
                });
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
            //new data
            case R.id.please:
                //hidden Already
                return true;
            //closed
            case R.id.youshouldwork:
                //hidden already

                return  true;

            case SEARCH_ID:
                search(); return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void search(){
        final   ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)prgandfaqviewweb.getLayoutParams();
        final ViewGroup.LayoutParams c=(ViewGroup.LayoutParams)prgandfaqviewweb.getLayoutParams();
        p.topMargin=60;
        p.height=10000;
        prgandfaqviewweb.setLayoutParams(p);
        container=(LinearLayout) findViewById(R.id.linearlayout3);
        nextbutton=new Button(this);
        nextbutton.setText("next");
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgandfaqviewweb.findNext(true);
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


        findbox=new EditText(this);
        findbox.setMinEms(30);
        findbox.setSingleLine(true);
        findbox.setHint("Search");
        findbox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&((keyCode==KeyEvent.KEYCODE_ENTER)))
                    prgandfaqviewweb.findAll(findbox.getText().toString());
                try{

                    Method m=WebView.class.getMethod("setFindisup",Boolean.TYPE);
                    m.invoke(prgandfaqviewweb,true);

                }catch (Exception ignored){}
                return false;
            }
        });
        container.addView(findbox);


    }


}
