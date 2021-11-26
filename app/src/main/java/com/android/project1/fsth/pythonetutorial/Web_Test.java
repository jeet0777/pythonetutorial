package com.android.project1.fsth.pythonetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;


//import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7

public class Web_Test extends AppCompatActivity {
    shared shared;
    WebView tryweb;
    font font_prefer;
    FloatingActionButton ReaderMode,fab2;
    public static final String PREFERENCES = "PREFERENCES_NAME";
    public static final String WEB_LINKS = "links";
    public static final String WEB_TITLE = "title";
//test there
String current_page_url ;

    public LinearLayout container;
    Button nextbutton,closebutton;
    private EditText findbox;
    private  static  final int SEARCH_ID=Menu.FIRST;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        shared=new shared(this);
        shared.LoadMode();//this will load Dark Mode


        super.onCreate(savedInstanceState);
        font_prefer = new font(this);

        setContentView(R.layout.activity_web__test);

        coordinatorLayout=findViewById(R.id.webTest_cordnitor);


        tryweb=findViewById(R.id.tryweb);
        int a = font_prefer.loadfont();

        tryweb.getSettings().setDefaultFontSize((int) a);

        tryweb.getSettings().setJavaScriptEnabled(true);
        if(getIntent().getStringExtra("url")!=null)
        {
            current_page_url=getIntent().getStringExtra("url");
            tryweb.loadUrl(current_page_url);



            tryweb.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    current_page_url=url;
                    invalidateOptionsMenu();
                 }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    tryweb.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
                        tryweb.loadUrl(request.getUrl().toString());

                    return super.shouldOverrideUrlLoading(view, request);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    invalidateOptionsMenu();
                    if(shared.loadnightmode()) {
                        tryweb.evaluateJavascript("javascript:document.body.style.color=\"white\"", null);
                        tryweb.setBackgroundColor(Color.BLACK);
                    }
                invalidateOptionsMenu();
                    current_page_url=url;
                    super.onPageFinished(view, url);

                }
            });

            return;



        }
        if(getIntent().getStringExtra("get")!=null){
            current_page_url="file:///android_asset/Tutorial/";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(current_page_url+getIntent().getStringExtra("get")
                +".html");

                    tryweb.loadUrl(stringBuilder.toString());

        tryweb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            current_page_url=url;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                tryweb.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
                tryweb.loadUrl(request.getUrl().toString());

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                invalidateOptionsMenu();
                if(shared.loadnightmode()) {
                    tryweb.evaluateJavascript("javascript:document.body.style.color=\"white\"", null);
                    tryweb.setBackgroundColor(Color.BLACK);
                    }
                super.onPageFinished(view, url);
                    }
                    });

            return;
        }//getINtent
        else{
            shared.DisplayMsg("Something Went Wrong\n");
            String html="<b><center>404<br/> NOt Found</center></b>";
//            tryweb.loadData(html,"text/html;charset=UTF-8",null);
            }



//            ReaderMode.setVisibility(View.VISIBLE);


    }



    @Override //option menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflates=getMenuInflater();
        inflates.inflate(R.menu.other,menu);
        if(!tryweb.canGoBack()){
            MenuItem please = menu.findItem(R.id.please);
            please.setVisible(true);
        //partial way for bookmark hidding dated 20-sept-2020
//tracking the Code cant means home idc
        }
        if(tryweb.canGoBack()){
            MenuItem hideact = menu.findItem(R.id.youshouldwork);
            hideact.setVisible(true);


        }
        menu.add(0,SEARCH_ID,0,"search");
        //new Data
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String links = sharedPreferences.getString(WEB_LINKS, null);

        if (links != null) {

            Gson gson = new Gson();
            ArrayList<String> linkList = gson.fromJson(links, new TypeToken<ArrayList<String>>() {
            }.getType());

            if (linkList.contains(current_page_url)) {
                menu.getItem(0).setIcon(R.drawable.ic_bookmark_black_24dp);
            } else {
                menu.getItem(0).setIcon(R.drawable.ic_bookmark_border_black_24dp);
            }
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_bookmark_border_black_24dp);
        }
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
                String message;

                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                String jsonLink = sharedPreferences.getString(WEB_LINKS, null);
                String jsonTitle = sharedPreferences.getString(WEB_TITLE, null);


                if (jsonLink != null && jsonTitle != null) {

                    Gson gson = new Gson();
                    ArrayList<String> linkList = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
                    }.getType());

                    ArrayList<String> titleList = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
                    }.getType());

                    if (linkList.contains(current_page_url)) {

                        linkList.remove(current_page_url);
                        titleList.remove(tryweb.getTitle().trim());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                        editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                        editor.apply();

                        message = "Bookmark Removed";

                    } else {
                        linkList.add(current_page_url);
                        titleList.add(tryweb.getTitle().trim());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                        editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                        editor.apply();
                        invalidateOptionsMenu();

                        message = "Bookmarked";

                    }
                } else {

                    ArrayList<String> linkList = new ArrayList<>();
                    ArrayList<String> titleList = new ArrayList<>();
                    linkList.add(current_page_url);
                    titleList.add(tryweb.getTitle());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(WEB_LINKS, new Gson().toJson(linkList));
                    editor.putString(WEB_TITLE, new Gson().toJson(titleList));
                    editor.apply();

                    message = "Bookmarked";
                }

              Snackbar snackbar=
                Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG);
              snackbar.show();
                invalidateOptionsMenu();
                return true;
            //closed
            case R.id.youshouldwork:
                //Intent in =new Intent(this,BookmarkActivity.class);
                //String urlpass=tut.getUrl().toString();
                //in.putExtra("getpreviuosdata",urlpass);
                //startActivity(in);
//                startActivity(new Intent(this,BookmarkActivity.class)); finish();
            Intent intent=new Intent(Web_Test.this,BookmarkActivity.class);
            intent.putExtra("geturlb","hey android 3.3");
            startActivityForResult(intent,303);


                return  true;

            case SEARCH_ID:
                search(); return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void search(){
        // EditText editTex;
        //editTex=new EditText(this);
        //getActionBar().setCustomView(editTex);
        //getActionBar().setDisplayShowTitleEnabled(false);
//if(shared.loadnightmode()==true){ findbox.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);}else{}
        final   ViewGroup.MarginLayoutParams p=(ViewGroup.MarginLayoutParams)tryweb.getLayoutParams();
        final ViewGroup.LayoutParams c=(ViewGroup.LayoutParams)tryweb.getLayoutParams();
        p.topMargin=60;
        p.height=10000;
        tryweb.setLayoutParams(p);
        container=(LinearLayout) findViewById(R.id.linearlayout3);
        //LinearLayout container=new LinearLayout(tutorial.this);
        //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.)
        nextbutton=new Button(this);
        nextbutton.setText("next");
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryweb.findNext(true);
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
                    tryweb.findAll(findbox.getText().toString());
                try{

                    Method m=WebView.class.getMethod("setFindisup",Boolean.TYPE);
                    m.invoke(tryweb,true);

                }catch (Exception ignored){}
                return false;
            }
        });
        container.addView(findbox);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    if(resultCode==303){
        //Just for Assurance
    }
      super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if(tryweb.canGoBack()){
//        first.setBackgroundColor(Color.parseColor("#000000"));
            tryweb.goBack();
            //getTitle.setVisibility(View.GONE);
        }else
            super.onBackPressed();


    }

    public void hellowfab(View view) {

    fab2=findViewById(R.id.fab2);
    fab2.show();//show fab2
    ReaderMode.hide();//hide this to show new one

    }

    public void second(View view) {

        ReaderMode.hide();//hide fab1 to display fab2

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().show();

        fab2.hide();//here clicked so the readermode gets off
                    // and this button hides

    }
}
